$scriptDir = Split-Path $MyInvocation.MyCommand.Path -Parent
docker-compose -f $scriptDir/db/docker-compose.yml up
