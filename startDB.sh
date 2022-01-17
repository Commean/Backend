#!/bin/sh
SCRIPT_DIR=$(dirname "$0")
docker-compose -f $SCRIPT_DIR/db/docker-compose.yml up
