# ✨ QUICK START - Docker Only

## Prerequisites

**Install Docker Desktop:**
- Download: https://www.docker.com/products/docker-desktop
- Install and start it

---

## 🎯 Start Server (2 Steps)

### Step 1: Navigate to site directory
```bash
cd /Users/ranjeet/Shunya-0/knowledge_core/site
```

### Step 2: Start Docker
```bash
docker-compose up
```

**Open:** http://localhost:4000

**That's it!** ✅

---

## ⏸️ Stop Server

Press `Ctrl+C` in terminal

Or run:
```bash
docker-compose down
```

---

## 🔄 Clean Restart

```bash
# Stop and remove everything
docker-compose down -v

# Remove generated files
rm -rf _site vendor .jekyll-cache

# Start fresh
docker-compose up
```

---

## 🐛 Troubleshooting

### Docker not found?
Install Docker Desktop: https://www.docker.com/products/docker-desktop

### Docker not running?
```bash
open -a Docker
```

### Port 4000 in use?
```bash
lsof -ti:4000 | xargs kill -9
```

---

## 📖 Need More Help?

See **[README.md](../README.md)** for full documentation.

---

**ॐ तत् सत्**

*From Shunya came Docker, from Docker came the server.* 🐳
