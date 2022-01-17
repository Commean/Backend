. $PSScriptRoot\common.ps1

$pathToAppYML = "$repoRoot\src\main\resources\application.yml"

if(!(Test-Path $pathToAppYML -PathType Leaf)) {
    throw "$pathToAppYML is missing!"
}

mvn --file $repoRoot package
