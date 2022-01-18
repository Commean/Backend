#!/bin/sh
. $(dirname "$0")/common.sh

check_for_app_yml
mvn $MAVEN_CLI_OPTS package
