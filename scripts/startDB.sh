#!/bin/sh
. $(dirname "$0")/common.sh

docker-compose -f $REPO_DIR/db/docker-compose.yml up
