package dev.y0.client.license;

import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Enumeration;

public class HWIDUtil {

    public static String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("os.name", ""));
        sb.append(System.getProperty("os.arch", ""));
        sb.append(System.getProperty("user.name", ""));

        String procId = System.getenv("PROCESSOR_IDENTIFIER");
        if (procId != null) sb.append(procId);

        String compName = System.getenv("COMPUTERNAME");
        if (compName != null) sb.append(compName);

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                byte[] mac = ni.getHardwareAddress();
                if (mac != null && mac.length > 0) {
                    for (byte b : mac) sb.append(String.format("%02x", b));
                    break;
                }
            }
        } catch (Exception ignored) {}

        return sha256(sb.toString());
    }

    private static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }
}
