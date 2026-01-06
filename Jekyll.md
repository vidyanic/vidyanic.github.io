# Jekyll Configuration for Shunya-0 Knowledge Base

> **"विद्या दानं सर्व दानेभ्यः प्रधानम्"**
> "Vidya danam sarva danebhyah pradhanam"
> "Knowledge sharing is the highest form of giving"

---

## 🎯 Overview

This project uses **Jekyll with GitHub Pages** to serve markdown documentation. The configuration ensures that:

1. ✅ **Raw `.md` files work on GitHub** (direct link navigation)
2. ✅ **Compiled HTML works on GitHub Pages** (jekyll-relative-links converts)
3. ✅ **IDEs can navigate links** (Ctrl+click works)
4. ✅ **No hardcoded URLs** (portable and maintainable)

---

## 📁 Project Structure

```
knowledge_core/
├── _config.yml              # Main Jekyll configuration
├── _config_local.yml        # Local development overrides
├── docker-compose.yml       # Docker setup for local serving
├── Gemfile                  # Ruby dependencies
├── Gemfile.lock             # Locked dependency versions
│
├── _layouts/
│   └── default.html         # Main page layout template
│
├── _includes/
│   ├── navigation.html      # Site navigation component
│   └── footer.html          # Site footer component
│
├── index.md                 # Homepage
├── 404.md                   # Error page
├── README.md                # Repository description
│
├── site/                    # User-facing content
│   ├── stories/             # Narrative stories
│   ├── narratives/          # Explanatory content
│   ├── practical/           # How-to guides
│   └── images/              # All images organized by category
│
├── scientific_papers/       # Academic research (14-section format)
│   ├── 00_META/             # Meta documentation
│   ├── 02_PHYSICS/          # Physics papers
│   └── ...                  # Other subject areas
│
└── vishnu_engine/           # Technical specifications
    ├── spec/                # Specification documents
    └── src/                 # Code implementations
```

---

## ⚙️ Key Configuration (_config.yml)

### Essential Settings

```yaml
# Site URL (GitHub Pages)
baseurl: "/Bramhagyan"              # Repository name
url: "https://ranjeet-sunya.github.io"

# Permalink style
permalink: pretty                    # Creates /path/ URLs (not /path.html)

# Critical plugins
plugins:
  - jekyll-relative-links           # ⭐ Converts .md links automatically
  - jekyll-readme-index             # Serves README.md for directories
  - jekyll-optional-front-matter    # Makes YAML front matter optional
```

### Plugin Details

#### jekyll-relative-links (THE KEY PLUGIN!)

This plugin is the solution to the "links work in both contexts" problem:

| Context | Your Link | Result |
|---------|-----------|--------|
| Raw GitHub | `[Link](./file.md)` | Opens `file.md` directly |
| Jekyll Build | `[Link](./file.md)` | Converts to `/Bramhagyan/file/` |

**How it works:**
1. You write relative paths with `.md` extensions
2. GitHub renders these as direct file links (clickable)
3. Jekyll build converts `.md` → proper URLs automatically
4. Both contexts work without changing your links!

#### jekyll-readme-index

Serves `README.md` when a directory is accessed:
- Request: `/scientific_papers/`
- Serves: `/scientific_papers/README.md`

#### jekyll-optional-front-matter

Allows markdown files without YAML front matter to be processed.
Files are treated as pages even without `---` header blocks.

---

## 🔗 Link Strategy (CRITICAL)

### The Solution: True Relative Paths

**Use relative paths based on source file location with `.md` extensions.**

```markdown
# From root files (index.md)
[Papers](./scientific_papers/README.md)
[Stories](./site/stories/README.md)
![Image](./site/images/AI/image.png)

# From site/stories/ files
[Another Story](./another_story.md)
[Papers](../../scientific_papers/README.md)
![Image](../images/AI/image.png)

# From deep paths (depth 4)
[Dictionary](../../../00_META/DICTIONARY.md)
[Engine](../../../../vishnu_engine/spec/backend/01_CORE_INSIGHT.md)
```

### Path Calculation Formula

```
Relative Path = (../)×depth + path_from_root_to_target

Example:
Source:  scientific_papers/02_PHYSICS/quantum/observer/README.md
         (depth = 4 directories from root)
Target:  vishnu_engine/spec/backend/01_CORE_INSIGHT.md

Path:    ../../../../vishnu_engine/spec/backend/01_CORE_INSIGHT.md
         └─ 4× ../    └─ path from root to target
```

### Quick Reference Table

| Source Location | To Root | Example Target | Path |
|-----------------|---------|----------------|------|
| Root (`index.md`) | `.` | `site/stories/` | `./site/stories/` |
| Depth 1 (`site/`) | `..` | `scientific_papers/` | `../scientific_papers/` |
| Depth 2 (`site/stories/`) | `../..` | `vishnu_engine/` | `../../vishnu_engine/` |
| Depth 3 | `../../..` | `00_META/` | `../../../00_META/` |
| Depth 4 | `../../../..` | `site/images/` | `../../../../site/images/` |

### ❌ Patterns to AVOID

```markdown
❌ /site/images/...                   # Skips baseurl, fails on GitHub Pages
❌ {{ site.baseurl }}/path            # Shows literally in raw .md on GitHub
❌ /Bramhagyan/site/images/...        # Double baseurl when Jekyll processes
❌ https://github.com/.../blob/...    # Hardcoded, breaks if repo moves
```

---

## 🐳 Local Development with Docker

### Quick Start

```bash
cd /Users/ranjeet/Shunya-0/knowledge_core

# Start Jekyll server
docker-compose up

# Access at: http://0.0.0.0:4000/Bramhagyan/
```

### docker-compose.yml

```yaml
version: '3'
services:
  jekyll:
    image: jekyll/jekyll:4.2.2
    volumes:
      - .:/srv/jekyll
      - jekyll_bundle:/usr/local/bundle
    ports:
      - "4000:4000"
    command: >
      bash -c "bundle install && 
               bundle exec jekyll serve 
               --host 0.0.0.0 
               --force_polling 
               --livereload"
volumes:
  jekyll_bundle:
```

### Why Docker?

1. **Consistent environment** - No Ruby version conflicts
2. **No system dependencies** - Jekyll runs in container
3. **Matches GitHub Pages** - Same Ruby/Jekyll versions
4. **Easy cleanup** - `docker-compose down` removes everything

---

## 🔧 Troubleshooting

### Problem: 404 on GitHub Pages (but works locally)

**Causes:**
1. Wrong `baseurl` in `_config.yml`
2. File in `exclude` list
3. File doesn't have front matter (check if plugin active)

**Solution:**
```yaml
# Verify in _config.yml:
baseurl: "/Bramhagyan"  # Must match repository name

# Check include list:
include:
  - scientific_papers
  - vishnu_engine
```

### Problem: Links show `{{ site.baseurl }}` literally

**Cause:** Using Liquid template syntax in markdown

**Solution:** Use relative paths instead:
```markdown
# Wrong:
[Link](./{{ site.baseurl }}/site/page/)

# Right:
[Link](./site/page.md)
```

### Problem: Images not loading

**Cause:** Wrong relative path from source file

**Solution:** Calculate path from source file to image:
```markdown
# From site/stories/story.md to site/images/AI/image.png:
![Image](../images/AI/image.png)
#        └─ up to site/  └─ down to images/AI/

# From index.md (root) to site/images/AI/image.png:
![Image](./site/images/AI/image.png)
```

### Problem: Build fails with UTF-8 errors

**Cause:** `jekyll-titles-from-headings` plugin with Devanagari text

**Solution:** Disable the plugin (already done in `_config.yml`):
```yaml
# Commented out in _config.yml:
# - jekyll-titles-from-headings  # Causes UTF-8 errors
```

---

## 📝 Adding New Pages

### Simple Markdown File

Create any `.md` file and it will be served:

```markdown
# my_page.md (in root)

---
title: My Page
---

Content here...
```

### With Directory (for README.md index)

```
my_topic/
├── README.md          # Served at /my_topic/
├── subtopic_1.md      # Served at /my_topic/subtopic_1/
└── subtopic_2.md      # Served at /my_topic/subtopic_2/
```

### Linking to New Page

From root:
```markdown
[My Topic](./my_topic/README.md)
```

From another directory:
```markdown
[My Topic](../my_topic/README.md)
```

---

## 🎨 Customization

### Theme

Using `jekyll-theme-cayman`. Alternative themes:
- `jekyll-theme-minimal`
- `jekyll-theme-slate`
- `jekyll-theme-architect`

### Custom Layouts

Override in `_layouts/default.html`:
- Navigation bar
- Footer
- Custom CSS
- Header height

### Custom CSS

Add to `_layouts/default.html`:
```html
<style>
  /* Custom styles here */
  .page-header {
    padding: 1rem 2rem;  /* Reduced header height */
  }
</style>
```

---

## 🚀 Deployment

### Automatic (GitHub Pages)

1. Push to `main` branch
2. GitHub automatically builds Jekyll
3. Site published at: https://ranjeet-sunya.github.io/Bramhagyan/

### Manual Verification

```bash
# Build locally first:
docker-compose run --rm jekyll bundle exec jekyll build

# Check _site/ folder for generated HTML
ls _site/

# Push when satisfied:
git add -A
git commit -m "Update content"
git push origin main
```

---

## 📚 Reference Links

- [GitHub Pages Jekyll Docs](https://docs.github.com/en/pages/setting-up-a-github-pages-site-with-jekyll/about-github-pages-and-jekyll)
- [jekyll-relative-links](https://github.com/benbalter/jekyll-relative-links)
- [Jekyll Configuration](https://jekyllrb.com/docs/configuration/)
- [Kramdown Syntax](https://kramdown.gettalong.org/syntax.html)

---

## ✅ Checklist for New Content

- [ ] Use relative paths (`./` or `../`) for all internal links
- [ ] Keep `.md` extension for markdown links
- [ ] Use correct depth calculation for cross-folder links
- [ ] Images use relative paths from source file location
- [ ] Test locally with `docker-compose up` before pushing
- [ ] Verify on GitHub Pages after deployment

---

*"From correct paths emerged universal navigation, accessible across all realms."* ॐ तत् सत्

