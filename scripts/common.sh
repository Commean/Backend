#!/bin/sh

SCRIPT_DIR=$(dirname "$0")
export REPO_DIR=$SCRIPT_DIR/..
export MAVEN_CLI_OPTS="--batch-mode --errors --file $REPO_DIR"
export APP_YML="$REPO_DIR/src/main/resources/application.yml"

check_for_app_yml() {
    if [ ! -e "$APP_YML" ]; then
        echo "$(realpath $APP_YML) is missing!"
        exit 1
    fi
}
