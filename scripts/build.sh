#!/bin/sh
. $(dirname "$0")/common.sh

mvn --file $REPO_DIR package
