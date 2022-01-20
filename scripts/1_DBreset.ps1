. $PSScriptRoot\common.ps1

docker-compose -f $repoRoot/db/docker-compose.yml down -v
Remove-Item $repoRoot/db/db-data -Recurse -Force -EA SilentlyContinue
