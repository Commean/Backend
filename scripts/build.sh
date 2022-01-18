#!/bin/sh
. $(dirname "$0")/common.sh

export APP_YML="$REPO_DIR/src/main/resources/application.yml"

if [ ! -e "$APP_YML" ]
then
    echo "$(realpath $APP_YML) is missing!"
    exit 1
fi

mvn --file $REPO_DIR package
