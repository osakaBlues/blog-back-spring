#!/bin/sh
echo "Cleaning up..."
cd /home/ubuntu/deploy/blog-back-spring
docker compose down
rm -rf /home/ubuntu/deploy/blog-back-spring
