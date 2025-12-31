#!/bin/bash
# Link Validation Script for GitHub Pages

echo "╔═══════════════════════════════════════════════════════════════════════════╗"
echo "║                                                                           ║"
echo "║        🔍 GITHUB PAGES LINK VALIDATOR                                     ║"
echo "║                                                                           ║"
echo "╚═══════════════════════════════════════════════════════════════════════════╝"
echo ""

cd /Users/ranjeet/Shunya-0/knowledge_core

total_links=0
broken_links=0

echo "📊 VALIDATING index.md"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""

# Extract all  links and check if corresponding .md files exist
grep -o '\./[^)]*\' index.md | sort -u | while read -r htmllink; do
    # Convert ./path/file to path/file.md
    mdfile="${htmllink#./}"
    mdfile="${mdfile%}.md"
    
    ((total_links++))
    
    if [ ! -f "$mdfile" ]; then
        echo "❌ BROKEN: $htmllink"
        echo "   Missing file: $mdfile"
        echo ""
        ((broken_links++))
    else
        echo "✅ OK: $htmllink → $mdfile"
    fi
done

echo ""
echo "═══════════════════════════════════════════════════════════════════════════"
echo "📈 SUMMARY"
echo "═══════════════════════════════════════════════════════════════════════════"
echo ""
echo "All links in index.md have been validated."
echo ""
echo "Next steps:"
echo "1. Commit changes: git add index.md validate_links.sh"
echo "2. Push to GitHub: git push"
echo "3. Wait 2-3 minutes for GitHub Pages to rebuild"
echo "4. Test at: https://ranjeet-sunya.github.io/Bramhagyan/"
echo ""
