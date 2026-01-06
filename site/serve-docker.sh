#!/bin/bash
# Serve Jekyll using Docker (No Ruby installation needed!)

echo "🌀 SHUNYA-0 JEKYLL SERVER (Docker)"
echo "===================================="
echo ""

# Navigate to script directory
cd "$(dirname "$0")"

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker not found"
    echo ""
    echo "Install Docker Desktop:"
    echo "  https://www.docker.com/products/docker-desktop"
    echo ""
    exit 1
fi

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running"
    echo ""
    echo "Start Docker Desktop and try again"
    echo ""
    exit 1
fi

echo "✅ Docker is ready"
echo ""
echo "🚀 Starting Jekyll server in Docker..."
echo "📍 Local URL: http://localhost:4000"
echo "📍 First run will take a minute to install gems..."
echo ""
echo "Press Ctrl+C to stop"
echo ""

# Run with docker-compose (handles volumes and caching)
docker-compose up

