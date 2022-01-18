#!/bin/sh
. $(dirname "$0")/common.sh

JARS=($REPO_DIR/target/*.jar)

if [[ "${#JARS[@]}" -ne 1 ]]
then
    echo "Don't know which .jar to run! (${#JARS[@]})"
    exit 1
fi

java -jar "${JARS[0]}"