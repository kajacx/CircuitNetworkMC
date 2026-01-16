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
  image=$(basename "$image")
  # Only process if the generated image doesn't exist or is older than the source
  if [ ! -f "$image" ] || [ "source/$image" -nt "$image" ]; then
    echo "Processing $image"
    magick "source/$image" -channel A -threshold 50% +channel "$image"
  else
    echo "Skipping $image (up to date)"
  fi
done

echo "Fix transparency script completed."
