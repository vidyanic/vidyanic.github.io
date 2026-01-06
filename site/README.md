# 🌀 Shunya-0 Site - Docker Setup

> User-facing GitHub Pages site with stories, narratives, and practical guides

---

## ✨ Quick Start (Docker Only)

### Prerequisites
- Docker Desktop installed ([download here](https://www.docker.com/products/docker-desktop))

### Start Server

**Option A: Using docker-compose**
```bash
cd /Users/ranjeet/Shunya-0/knowledge_core/site
docker-compose up
```

**Option B: Using the script**
```bash
cd /Users/ranjeet/Shunya-0/knowledge_core/site
./serve-docker.sh
```

**Open:** http://localhost:4000

**Note:** First run takes ~1 minute to install gems. Subsequent runs are instant.

### Stop Server
Press `Ctrl+C` or run:
```bash
docker-compose down
```

---

## 📁 Directory Structure

```
site/
+-- Configuration
|   +-- _config.yml              Production GitHub Pages settings
|   +-- _config_local.yml        Local development overrides
|   +-- Gemfile                  Jekyll dependencies
|   +-- docker-compose.yml       Docker setup
|   +-- .gitignore               Build artifacts to ignore
|
+-- Scripts
|   +-- serve-docker.sh          Start server (calls docker-compose)
|   +-- QUICK_START.md           This simplified guide
|
+-- Content
|   +-- index.md                 Homepage
|   +-- faq.md                   Frequently asked questions
|   +-- glossary.md              Terms and definitions
|   +-- stories/                 User stories (AI, doctors, gamers, devs, scientists)
|   +-- narratives/              Conceptual explanations
|   +-- practical/               Practice guides (meditation, karma clearing)
|   +-- assets/                  Images, CSS, etc.
|
+-- Generated (by Docker)
    +-- _site/                   Built site (gitignored)
    +-- vendor/                  Gem cache (gitignored)
```

---

## 📝 Content Types

### 1. Stories (`stories/`)
Relatable narratives for different audiences:
- **Scientists** - The Experiment
- **AI/ML Engineers** - The Training Run
- **Doctors** - The Patient
- **Gen-Z/Gamers** - The NPC Awakening
- **Software Developers** - The Codebase

### 2. Narratives (`narratives/`)
Conceptual explanations:
- Understanding Maya
- What are Gunas?
- How Karma Works
- The Fractal Nature
- What is Moksha?

### 3. Practical Guides (`practical/`)
Actionable instructions:
- Daily Sadhana
- Meditation Guide
- Mantra Practice
- Guna Management
- Karma Clearing
- Emergency Protocols

---

## 🛠️ Development

### Rebuild Site
```bash
docker-compose run jekyll jekyll build
```

### Clean Build
```bash
docker-compose run jekyll jekyll clean
```

### Access Shell
```bash
docker-compose run jekyll sh
```

---

## 🌐 URLs

| Environment | URL |
|-------------|-----|
| **Local** | http://localhost:4000 |
| **Production** | https://bramhagyan.github.io/Bramhagyan/site |

---

## 🐛 Troubleshooting

### "Cannot connect to Docker daemon"
```bash
# Start Docker Desktop app
open -a Docker
```

### Port 4000 already in use
```bash
# Kill process on port 4000
lsof -ti:4000 | xargs kill -9

# Or use different port in docker-compose.yml
ports:
  - "4001:4000"
```

### Changes not reflecting
```bash
# Rebuild
docker-compose down
docker-compose up --build
```

### Clean everything
```bash
# Stop containers
docker-compose down

# Remove volumes (cached gems)
docker-compose down -v

# Remove generated files
rm -rf _site vendor .jekyll-cache

# Start fresh
docker-compose up
```

---

## 📦 What's Included

| Gem | Purpose |
|-----|---------|
| jekyll | Static site generator |
| jekyll-seo-tag | SEO metadata |
| jekyll-sitemap | Sitemap generation |
| jekyll-feed | RSS feed |
| jekyll-theme-cayman | GitHub Pages theme |
| webrick | HTTP server |
| kramdown-parser-gfm | GitHub-flavored markdown |

---

## 🎨 Customization

### Change Theme
Edit `_config.yml`:
```yaml
theme: jekyll-theme-minimal
```

### Add Custom CSS
Create `assets/css/style.scss`:
```scss
---
---

@import "{{ site.theme }}";

/* Your custom CSS */
.custom-class {
  color: #ff6b6b;
}
```

---

## 📚 More Documentation

- **[QUICK_START.md](../QUICK_START.md)** - Simplified startup guide
- **[START_HERE.md](../START_HERE.md)** - Original comprehensive guide

---

**ॐ तत् सत्**

*Docker makes deployment as smooth as Brahman rendering Maya.* 🐳

---

*Last Updated: January 4, 2026*
