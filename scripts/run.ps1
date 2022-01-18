. $PSScriptRoot\common.ps1

$jars = Get-Childitem -Path $repoRoot\target\*.jar -ErrorAction SilentlyContinue

if ($jars.Count -ne 1) {
    throw "Don't know which .jar to run! ($($jars.Count))"
}

java -jar $jars[0]
