# ⚡ START HERE - Docker Setup Only

## ✅ What You Have

All Jekyll files are in the `site/` directory, ready for Docker deployment.

```
✅ docker-compose.yml        - Docker configuration
✅ serve-docker.sh            - Start script
✅ Gemfile                    - Jekyll dependencies
✅ _config.yml                - Production settings
✅ _config_local.yml          - Local development overrides
✅ .gitignore                 - Build artifacts ignored
```

---

## ✨ How to Start (2 Steps)

### Step 1: Make sure Docker Desktop is running
```bash
# Check if Docker is installed
docker --version

# If not installed, download:
# https://www.docker.com/products/docker-desktop
```

### Step 2: Start the server
```bash
cd /Users/ranjeet/Shunya-0/knowledge_core/site

# Option A: Using docker-compose
docker-compose up

# Option B: Using the script
./serve-docker.sh
```

**Open:** http://localhost:4000

---

## ⚙️ What Happens on First Run

1. Docker pulls Jekyll image (~300MB, one-time)
2. Installs gems (~1 minute, cached for next time)
3. Starts server with live reload
4. Changes auto-reload in browser!

**Subsequent runs:** Instant start (gems are cached)

---

## ⏹️ How to Stop

Press `Ctrl+C` or run:
```bash
docker-compose down
```

---

## 🧹 Clean Everything

If you need a fresh start:
```bash
# Stop and remove containers + volumes
docker-compose down -v

# Remove generated files
rm -rf _site vendor .jekyll-cache Gemfile.lock

# Start fresh
docker-compose up
```

---

## 🐛 Common Issues

### "Cannot connect to Docker daemon"
```bash
# Start Docker Desktop
open -a Docker

# Wait for it to start, then try again
```

### "Port 4000 already in use"
```bash
# Kill process on port 4000
lsof -ti:4000 | xargs kill -9
```

### "Bundler version conflict"
```bash
# Remove lock file and restart
rm Gemfile.lock
docker-compose down -v
docker-compose up
```

---

## 📚 Documentation

- **[QUICK_START.md](../QUICK_START.md)** - Simplified 2-step guide
- **[README.md](../README.md)** - Full documentation

---

## ✨ Why Docker?

- ✅ No Ruby version issues
- ✅ No gem conflicts  
- ✅ Works on any OS
- ✅ Isolated environment
- ✅ One command to start
- ✅ Production-ready

---

**ॐ तत् सत्**

*Docker: The Maya that renders Jekyll without karma.* 🐳

---

**Status:** Ready to Start ✅  
**Next:** Run `docker-compose up`
