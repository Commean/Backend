$repoRoot = Split-Path $PSScriptRoot -Parent
$mavenCLIOptions =  ("--batch-mode", "--errors", "--file", $repoRoot)
$pathToAppYML = "$repoRoot\src\main\resources\application.yml"

function Check-For-App-YML {
    if(!(Test-Path $pathToAppYML -PathType Leaf)) {
        throw "$pathToAppYML is missing!"
    }
}
