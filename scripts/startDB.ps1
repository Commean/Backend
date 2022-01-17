$scriptDir = Split-Path $MyInvocation.MyCommand.Path -Parent
$repoRoot = Split-Path $scriptDir -Parent
docker-compose -f $repoRoot/db/docker-compose.yml up
