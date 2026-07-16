#!/bin/bash
# Auto-sync script for Data Structures And Algorithm repo
# Runs every 12 hours via macOS LaunchAgent

REPO_DIR="/Users/tatviksinha/Desktop/Data Structures And Algorithm"
LOG_FILE="$REPO_DIR/.git-auto-sync.log"
TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

echo "[$TIMESTAMP] Starting auto-sync..." >> "$LOG_FILE"

cd "$REPO_DIR" || { echo "[$TIMESTAMP] ERROR: Could not cd to repo" >> "$LOG_FILE"; exit 1; }

# Check if there are any changes (tracked or untracked)
if [ -z "$(git status --porcelain)" ]; then
    echo "[$TIMESTAMP] No changes detected. Skipping." >> "$LOG_FILE"
    exit 0
fi

# Stage all changes
git add -A

# Commit with a descriptive auto-generated message
CHANGED_FILES=$(git diff --cached --name-only | head -10)
FILE_COUNT=$(git diff --cached --name-only | wc -l | tr -d ' ')

COMMIT_MSG="auto-sync: $FILE_COUNT file(s) changed on $TIMESTAMP"

git commit -m "$COMMIT_MSG" -m "Changed files:" -m "$CHANGED_FILES"

# Push to remote
if git push origin main 2>> "$LOG_FILE"; then
    echo "[$TIMESTAMP] Successfully pushed $FILE_COUNT file(s)." >> "$LOG_FILE"
else
    echo "[$TIMESTAMP] ERROR: Push failed. Will retry next cycle." >> "$LOG_FILE"
    exit 1
fi
