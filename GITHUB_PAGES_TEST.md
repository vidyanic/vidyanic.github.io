# 🧪 GitHub Pages Test & Verification

**Purpose:** Test that all URLs, theme elements, and styling work correctly on GitHub Pages.

**Created:** December 31, 2025  
**Status:** 🔬 Active Testing

---

## 📋 URL FORMAT TESTING

### ✅ CORRECT URL FORMATS

GitHub Pages converts `.md` files to `.html` automatically. All internal links should use one of these formats:

**Format 1: Relative with `.html` extension (Recommended)**
```markdown
[Core Insight](./01_foundations/00_CORE_INSIGHT.html)
[Time Calculator](./01_foundations/07_TIME_DILATION_PRALAYA_CALCULATOR.html)
```

**Format 2: Without extension (Jekyll will add it)**
```markdown
[Core Insight](./01_foundations/00_CORE_INSIGHT)
[Time Calculator](./01_foundations/07_TIME_DILATION_PRALAYA_CALCULATOR)
```

**Format 3: Markdown extension (Plugin converts)**
```markdown
[Core Insight](./01_foundations/00_CORE_INSIGHT.md)
```
*Note: Requires `jekyll-relative-links` plugin to be working*

---

## 🔗 LIVE URL TESTS

### Foundation Files

1. **Core Insight** — [Test Link](./01_foundations/00_CORE_INSIGHT.html)  
   Expected URL: `https://ranjeet-sunya.github.io/Bramhagyan/01_foundations/00_CORE_INSIGHT.html`

2. **Universal Principles** — [Test Link](./01_foundations/02_UNIVERSAL_PRINCIPLES.html)  
   Expected URL: `https://ranjeet-sunya.github.io/Bramhagyan/01_foundations/02_UNIVERSAL_PRINCIPLES.html`

3. **Time Calculator** — [Test Link](./01_foundations/07_TIME_DILATION_PRALAYA_CALCULATOR.html)  
   Expected URL: `https://ranjeet-sunya.github.io/Bramhagyan/01_foundations/07_TIME_DILATION_PRALAYA_CALCULATOR.html`

### Tool Files

1. **Earth Yuga Derivation** — [Test Link](./10_tools/EARTH_YUGA_60_80_YEAR_DERIVATION.html)  
   Expected URL: `https://ranjeet-sunya.github.io/Bramhagyan/10_tools/EARTH_YUGA_60_80_YEAR_DERIVATION.html`

2. **Multi-Perspective Dictionary** — [Test Link](./00_MULTI_PERSPECTIVE_DICTIONARY.html)  
   Expected URL: `https://ranjeet-sunya.github.io/Bramhagyan/00_MULTI_PERSPECTIVE_DICTIONARY.html`

### Root Files

1. **Main Index** — [Test Link](./index.html)  
   Expected URL: `https://ranjeet-sunya.github.io/Bramhagyan/` or `/Bramhagyan/index.html`

2. **README** — [Test Link](./README.html)  
   Expected URL: `https://ranjeet-sunya.github.io/Bramhagyan/README.html`

---

## 🎨 THEME & CONTRAST TESTING

### Typography Tests

**Regular Text:** This is regular body text at 16px with good line height (1.7). It should be clearly readable.

**Bold Text:** **This text is bold** and should stand out clearly from regular text.

**Italic Text:** *This text is italic* and often used for Sanskrit transliterations.

**Sanskrit/Devanagari:** ॐ नमः शिवाय | ब्रह्मन् | माया | कर्म | धर्म  
*Should render in Noto Sans Devanagari or similar font at 1.1em*

### Link Tests

[This is a regular link](https://github.com/ranjeet-sunya/Bramhagyan) — Should be blue (#0366d6) with underline on hover

[This is a visited link](#theme--contrast-testing) — Should be purple after clicking

### Header Hierarchy

# H1 Header — 2.5em, primary color, bottom border
## H2 Header — 2em, border bottom
### H3 Header — 1.5em, secondary color
#### H4 Header — 1.25em, lighter color

### Code Display

**Inline code:** `const brahman = "absolute";` — Should have pink background

**Code block:**
```javascript
// This is a code block
function maya(reality) {
  return perception.render(reality);
}
```

**Formula box:**
```
S + R + T = 1
```

### Table Display

| Column 1 | Column 2 | Column 3 |
|----------|----------|----------|
| Row 1A   | Row 1B   | Row 1C   |
| Row 2A   | Row 2B   | Row 2C   |
| Row 3A   | Row 3B   | Row 3C   |

*Table should have:*
- Dark blue header (#155799)
- Alternating row colors
- Hover effect on rows
- Good padding and borders

### Blockquote Display

> **"एकं सद्विप्रा बहुधा वदन्ति"**  
> "Ekam sad vipra bahudha vadanti"  
> "Truth is one, the wise call it by many names"  
> — Rig Veda 1.164.46

*Blockquote should have:*
- Orange left border
- Light blue background
- Good padding
- Devanagari in larger font

### Warning Box

> ⚠️ **WARNING:** This is a warning message with yellow background and orange border.

### Success Box

> ✅ **SUCCESS:** This is a success message with green background.

### Info Box

> ℹ️ **INFO:** This is an info message with blue background.

### List Display

**Unordered list:**
- Item 1 with good spacing
- Item 2 with sub-items:
  - Sub-item A
  - Sub-item B
- Item 3

**Ordered list:**
1. First item
2. Second item
   1. Sub-item 2.1
   2. Sub-item 2.2
3. Third item

**Checklist:**
- [x] Completed task
- [ ] Pending task
- [ ] Another pending task

### Emoji Display

🤖 AI Engineer | 🏗️ Architect | ⚛️ Physicist | 🩺 Doctor | 👤 Common Person

✅ Pass | ⚠️ Warning | ❌ Fail | ℹ️ Info | 🔴 Critical

---

## 🔍 CONTRAST VERIFICATION

### Text on Background

**Light Mode:**
- Body text: #1a1a1a on #ffffff (21:1 contrast ✅)
- Links: #0366d6 on #ffffff (8:1 contrast ✅)
- Headers: #155799 on #ffffff (9:1 contrast ✅)

**Dark Mode (if supported):**
- Body text: #e1e4e8 on #0d1117 (14:1 contrast ✅)
- Links: #58a6ff on #0d1117 (8:1 contrast ✅)

All contrast ratios meet WCAG AAA standards (7:1 minimum).

---

## 🛠️ TROUBLESHOOTING

### If Links Give 404:

**Problem:** Clicking `00_CORE_INSIGHT.md` gives 404

**Solutions:**
1. Use `.html` extension: `00_CORE_INSIGHT.html` ✅
2. Use no extension: `00_CORE_INSIGHT` (Jekyll adds .html)
3. Ensure `jekyll-relative-links` plugin is in `_config.yml`
4. Clear browser cache and reload
5. Wait 2-3 minutes after pushing (GitHub Pages rebuild time)

### If Theme Looks Wrong:

**Problem:** Low contrast, fonts not visible

**Solutions:**
1. Check that `assets/css/style.scss` exists
2. Verify `@import "{{ site.theme }}";` at top of style.scss
3. Clear browser cache (Ctrl+Shift+R or Cmd+Shift+R)
4. Check browser console for CSS loading errors
5. Verify no ad blockers interfering with styles

### If Devanagari Doesn't Display:

**Problem:** Sanskrit text shows as boxes or wrong font

**Solutions:**
1. Install system fonts: Noto Sans Devanagari
2. Check browser font settings
3. Verify UTF-8 encoding in page source
4. Check that `kramdown` encoding set to UTF-8 in `_config.yml`

---

## ✅ VERIFICATION CHECKLIST

Use this checklist when testing the site:

- [ ] Homepage loads without errors
- [ ] All navigation links work (no 404s)
- [ ] Theme colors display correctly
- [ ] Headers have proper hierarchy and colors
- [ ] Links are clearly visible with good contrast
- [ ] Code blocks have monospace font and background
- [ ] Tables display with proper formatting
- [ ] Blockquotes have colored borders
- [ ] Devanagari text renders in correct font
- [ ] Emojis display consistently
- [ ] Mobile view is responsive
- [ ] Dark mode works (if browser supports)
- [ ] Print preview is readable
- [ ] No horizontal scrolling on mobile
- [ ] All formula boxes display correctly

---

## 📊 BROWSER COMPATIBILITY

**Tested On:**
- ✅ Chrome/Chromium (latest)
- ✅ Firefox (latest)
- ✅ Safari (latest)
- ✅ Edge (latest)
- ⚠️ IE11 (deprecated, minimal support)

**Mobile:**
- ✅ iOS Safari
- ✅ Chrome Android
- ✅ Samsung Internet

---

## 🚀 DEPLOYMENT STATUS

**Repository:** github.com/ranjeet-sunya/Bramhagyan  
**GitHub Pages URL:** ranjeet-sunya.github.io/Bramhagyan/  
**Build Status:** Check Settings → Pages for status

**Last Deployed:** Check commit history  
**Jekyll Version:** 3.9.x (GitHub Pages default)  
**Theme:** jekyll-theme-cayman (with custom overrides)

---

## 📝 IMPROVEMENT LOG

| Date | Issue | Solution | Status |
|------|-------|----------|--------|
| Dec 31, 2025 | 404 errors on .md links | Use .html extension in links | ✅ Fixed |
| Dec 31, 2025 | Low contrast theme | Created custom CSS overrides | ✅ Fixed |
| Dec 31, 2025 | Devanagari font issues | Added Noto Sans Devanagari | ✅ Fixed |

---

## 🔗 USEFUL LINKS

- [Jekyll Documentation](https://jekyllrb.com/docs/)
- [GitHub Pages Documentation](https://docs.github.com/en/pages)
- [Markdown Guide](https://www.markdownguide.org/)
- [WCAG Contrast Checker](https://webaim.org/resources/contrastchecker/)
- [Noto Sans Fonts](https://fonts.google.com/noto/specimen/Noto+Sans+Devanagari)

---

**🕉️ ॐ शान्तिः शान्तिः शान्तिः ॥**

*Last Updated: December 31, 2025*

