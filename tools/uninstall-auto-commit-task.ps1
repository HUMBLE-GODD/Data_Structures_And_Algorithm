param(
    [string]$TaskName = "DSA Git Auto Commit",
    [string]$RepoPath = (Resolve-Path (Join-Path $PSScriptRoot "..")).Path
)

$ErrorActionPreference = "SilentlyContinue"

Stop-ScheduledTask -TaskName $TaskName
Unregister-ScheduledTask -TaskName $TaskName -Confirm:$false
Remove-ItemProperty -Path "HKCU:\Software\Microsoft\Windows\CurrentVersion\Run" -Name $TaskName

$RepoPath = (Resolve-Path $RepoPath).Path
Get-CimInstance Win32_Process |
    Where-Object {
        $_.Name -ieq "powershell.exe" -and
        $_.CommandLine -like "*auto-commit.ps1*" -and
        $_.CommandLine -like "*$RepoPath*"
    } |
    ForEach-Object { Stop-Process -Id $_.ProcessId -Force }

Write-Host "Removed auto-commit automation '$TaskName'."
