param(
    [string]$TaskName = "DSA Git Auto Commit",
    [string]$RepoPath = (Resolve-Path (Join-Path $PSScriptRoot "..")).Path,
    [switch]$Push
)

$ErrorActionPreference = "Stop"
$RepoPath = (Resolve-Path $RepoPath).Path
$scriptPath = Join-Path $RepoPath "tools\auto-commit.ps1"
$pushArgument = if ($Push) { " -Push" } else { "" }
$arguments = "-NoProfile -ExecutionPolicy Bypass -WindowStyle Hidden -File `"$scriptPath`" -RepoPath `"$RepoPath`"$pushArgument"

$action = New-ScheduledTaskAction -Execute "powershell.exe" -Argument $arguments
$trigger = New-ScheduledTaskTrigger -AtLogOn
$settings = New-ScheduledTaskSettingsSet -AllowStartIfOnBatteries -DontStopIfGoingOnBatteries -RestartCount 3 -RestartInterval (New-TimeSpan -Minutes 1)

try {
    Register-ScheduledTask -TaskName $TaskName -Action $action -Trigger $trigger -Settings $settings -Description "Automatically commits changes in $RepoPath" -Force | Out-Null
    Start-ScheduledTask -TaskName $TaskName

    Write-Host "Installed and started scheduled task '$TaskName'."
}
catch {
    $runKey = "HKCU:\Software\Microsoft\Windows\CurrentVersion\Run"
    Set-ItemProperty -Path $runKey -Name $TaskName -Value "powershell.exe $arguments"
    Start-Process -FilePath "powershell.exe" -ArgumentList $arguments -WindowStyle Hidden

    Write-Host "Scheduled task install failed, so installed and started per-user startup entry '$TaskName'."
}
