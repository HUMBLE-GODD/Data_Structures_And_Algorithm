param(
    [string]$TaskName = "DSA Git Auto Commit"
)

$ErrorActionPreference = "SilentlyContinue"

Stop-ScheduledTask -TaskName $TaskName
Unregister-ScheduledTask -TaskName $TaskName -Confirm:$false

Write-Host "Removed scheduled task '$TaskName'."
