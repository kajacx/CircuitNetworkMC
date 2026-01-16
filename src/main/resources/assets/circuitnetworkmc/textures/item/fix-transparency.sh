#!/usr/bin/sh
set -e

# Check if magick is installed
if ! command -v magick &> /dev/null; then
  echo "Warning: ImageMagick 'magick' command not found. You can ignore this if you are not editing textures."
  exit 0 # A fail-safe for other contributors since this will be part of the build process
fi

echo "Running the fix transparency script..."

# Sets transparency to 0 or 255 based on a 50% threshold, transparent pixels look awful on item models
for image in source/*.png; do
  echo "Processing $image"
  image=$(basename "$image")
  magick "source/$image" -channel A -threshold 50% +channel "$image"
done

echo "Fix transparency script completed."
