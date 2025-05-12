#!/bin/bash
# Create log directory if it doesn't exist
mkdir -p /logs

# Run the app and redirect stdout and stderr to a file
exec java -jar /app.jar > /logs/app.log 2>&1
