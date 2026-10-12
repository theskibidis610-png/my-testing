const express = require("express");
const cors = require("cors");
const crypto = require("crypto");
const fs = require("fs");
const path = require("path");

const app = express();
app.use(cors());
app.use(express.json());

const DB_PATH = path.join(__dirname, "keys.json");
const ADMIN_KEY = process.env.ADMIN_KEY || "change-this-admin-key";
const HWID_RESET_DAYS = 30;

function loadDb() {
  if (!fs.existsSync(DB_PATH)) fs.writeFileSync(DB_PATH, "{}");
  return JSON.parse(fs.readFileSync(DB_PATH, "utf8"));
}

function saveDb(db) {
  fs.writeFileSync(DB_PATH, JSON.stringify(db, null, 2));
}

function generateKey() {
  const chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  const seg = () =>
    Array.from({ length: 4 }, () => chars[crypto.randomInt(chars.length)]).join("");
  return `Y0-${seg()}-${seg()}-${seg()}`;
}

function daysSince(isoDate) {
  return (Date.now() - new Date(isoDate).getTime()) / (1000 * 60 * 60 * 24);
}

// Validate a key + HWID (called by the mod on launch / activation)
app.post("/api/validate", (req, res) => {
  const { key, hwid } = req.body;
  if (!key || !hwid) return res.json({ valid: false, message: "Missing key or HWID" });

  const db = loadDb();
  const entry = db[key];
  if (!entry) return res.json({ valid: false, message: "Invalid license key" });

  // Check expiry for monthly plans
  if (entry.plan === "monthly" && entry.expiresAt) {
    if (new Date(entry.expiresAt) < new Date()) {
      return res.json({ valid: false, message: "License expired", plan: entry.plan });
    }
  }

  // HWID binding
  if (!entry.hwid) {
    entry.hwid = hwid;
    entry.hwidBoundAt = new Date().toISOString();
    saveDb(db);
    return res.json({ valid: true, message: "License activated and bound to this PC", plan: entry.plan });
  }

  if (entry.hwid !== hwid) {
    return res.json({ valid: false, message: "License is bound to a different PC. Reset HWID on the dashboard.", plan: entry.plan });
  }

  res.json({ valid: true, message: "License valid", plan: entry.plan });
});

// Get license status (called by the dashboard)
app.post("/api/status", (req, res) => {
  const { key } = req.body;
  if (!key) return res.json({ error: "Missing key" });

  const db = loadDb();
  const entry = db[key];
  if (!entry) return res.json({ error: "Invalid license key" });

  const expired = entry.plan === "monthly" && entry.expiresAt && new Date(entry.expiresAt) < new Date();
  const daysSinceReset = entry.hwidBoundAt ? daysSince(entry.hwidBoundAt) : null;
  const canReset = daysSinceReset !== null && daysSinceReset >= HWID_RESET_DAYS;
  const daysUntilReset = daysSinceReset !== null ? Math.max(0, Math.ceil(HWID_RESET_DAYS - daysSinceReset)) : 0;

  res.json({
    plan: entry.plan,
    status: expired ? "expired" : "active",
    hwid: entry.hwid ? entry.hwid.substring(0, 12) + "..." : null,
    hwidBoundAt: entry.hwidBoundAt || null,
    expiresAt: entry.expiresAt || null,
    canReset,
    daysUntilReset,
    createdAt: entry.createdAt,
  });
});

// Reset HWID (called by the dashboard, 30-day cooldown)
app.post("/api/reset-hwid", (req, res) => {
  const { key } = req.body;
  if (!key) return res.json({ success: false, message: "Missing key" });

  const db = loadDb();
  const entry = db[key];
  if (!entry) return res.json({ success: false, message: "Invalid license key" });

  if (!entry.hwid) {
    return res.json({ success: false, message: "No HWID is bound to this key" });
  }

  const daysSinceReset = daysSince(entry.hwidBoundAt);
  if (daysSinceReset < HWID_RESET_DAYS) {
    const remaining = Math.ceil(HWID_RESET_DAYS - daysSinceReset);
    return res.json({ success: false, message: `HWID reset available in ${remaining} day(s)` });
  }

  entry.hwid = null;
  entry.hwidBoundAt = null;
  saveDb(db);

  res.json({ success: true, message: "HWID reset. Launch Y0 to bind to your new PC." });
});

// Admin: generate license keys
app.post("/api/admin/generate", (req, res) => {
  const { adminKey, plan, count = 1 } = req.body;
  if (adminKey !== ADMIN_KEY) return res.status(403).json({ error: "Unauthorized" });
  if (!["monthly", "lifetime"].includes(plan)) return res.json({ error: "Plan must be 'monthly' or 'lifetime'" });

  const db = loadDb();
  const keys = [];

  for (let i = 0; i < Math.min(count, 100); i++) {
    let key;
    do { key = generateKey(); } while (db[key]);

    db[key] = {
      plan,
      hwid: null,
      hwidBoundAt: null,
      createdAt: new Date().toISOString(),
      expiresAt: plan === "monthly" ? new Date(Date.now() + 30 * 24 * 60 * 60 * 1000).toISOString() : null,
    };
    keys.push(key);
  }

  saveDb(db);
  res.json({ keys });
});

// Admin: list all keys
app.post("/api/admin/list", (req, res) => {
  const { adminKey } = req.body;
  if (adminKey !== ADMIN_KEY) return res.status(403).json({ error: "Unauthorized" });

  const db = loadDb();
  const entries = Object.entries(db).map(([key, data]) => ({ key, ...data }));
  res.json({ keys: entries });
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Y0 License API running on port ${PORT}`));
