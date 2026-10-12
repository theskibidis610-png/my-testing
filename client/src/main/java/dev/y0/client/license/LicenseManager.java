package dev.y0.client.license;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.y0.client.Y0Client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

public class LicenseManager {

    // ====== CHANGE THIS to your deployed server URL ======
    public static final String API_BASE = "https://your-y0-api.vercel.app";
    // =====================================================

    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private final Path licenseFile;
    private String key;
    private boolean valid;
    private String plan;

    public LicenseManager(Path dataDir) {
        this.licenseFile = dataDir.resolve("license.json");
    }

    public boolean loadAndValidate() {
        if (!Files.exists(licenseFile)) return false;

        try {
            String content = Files.readString(licenseFile);
            JsonObject saved = GSON.fromJson(content, JsonObject.class);
            this.key = saved.get("key").getAsString();
            String savedHwid = saved.get("hwid").getAsString();
            String currentHwid = HWIDUtil.generate();

            if (!savedHwid.equals(currentHwid)) {
                this.valid = false;
                return false;
            }

            try {
                ValidationResult result = validateOnline(this.key, currentHwid);
                this.valid = result.success();
                this.plan = result.plan();
                if (this.valid) {
                    saved.addProperty("lastValidated", Instant.now().toString());
                    Files.writeString(licenseFile, GSON.toJson(saved));
                }
                return this.valid;
            } catch (Exception e) {
                // API unreachable — allow offline grace period (7 days)
                if (saved.has("lastValidated")) {
                    Instant lastCheck = Instant.parse(saved.get("lastValidated").getAsString());
                    if (Duration.between(lastCheck, Instant.now()).toDays() < 7) {
                        this.valid = true;
                        this.plan = saved.has("plan") ? saved.get("plan").getAsString() : "unknown";
                        Y0Client.LOGGER.warn("API unreachable, using offline grace period");
                        return true;
                    }
                }
                this.valid = false;
                return false;
            }
        } catch (Exception e) {
            Y0Client.LOGGER.error("Failed to load license", e);
            this.valid = false;
            return false;
        }
    }

    public ValidationResult activate(String key) {
        String hwid = HWIDUtil.generate();
        try {
            ValidationResult result = validateOnline(key, hwid);
            if (result.success()) {
                this.key = key;
                this.valid = true;
                this.plan = result.plan();
                saveLicense(key, hwid, result.plan());
            }
            return result;
        } catch (Exception e) {
            return new ValidationResult(false, "Connection failed: " + e.getMessage(), null);
        }
    }

    private ValidationResult validateOnline(String key, String hwid) throws Exception {
        JsonObject body = new JsonObject();
        body.addProperty("key", key);
        body.addProperty("hwid", hwid);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_BASE + "/api/validate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(body)))
                .build();

        HttpResponse<String> response = HTTP.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject json = GSON.fromJson(response.body(), JsonObject.class);

        boolean success = json.has("valid") && json.get("valid").getAsBoolean();
        String message = json.has("message") ? json.get("message").getAsString() : "Unknown error";
        String responsePlan = json.has("plan") ? json.get("plan").getAsString() : null;

        return new ValidationResult(success, message, responsePlan);
    }

    private void saveLicense(String key, String hwid, String plan) {
        try {
            Files.createDirectories(licenseFile.getParent());
            JsonObject data = new JsonObject();
            data.addProperty("key", key);
            data.addProperty("hwid", hwid);
            data.addProperty("plan", plan);
            data.addProperty("lastValidated", Instant.now().toString());
            Files.writeString(licenseFile, GSON.toJson(data));
        } catch (Exception e) {
            Y0Client.LOGGER.error("Failed to save license", e);
        }
    }

    public boolean isValid() { return valid; }
    public String getKey() { return key; }
    public String getPlan() { return plan; }

    public record ValidationResult(boolean success, String message, String plan) {}
}
