#!/bin/sh
SCRIPT_DIR=$(dirname "$0")
REPO_DIR=$SCRIPT_DIR/..
docker-compose -f $REPO_DIR/db/docker-compose.yml up
