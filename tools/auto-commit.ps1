param(
    [string]$RepoPath = (Resolve-Path (Join-Path $PSScriptRoot "..")).Path,
    [int]$DebounceSeconds = 10,
    [switch]$Push,
    [string]$Remote = "origin",
    [string]$Branch = "main"
)

$ErrorActionPreference = "Stop"
$RepoPath = (Resolve-Path $RepoPath).Path
$LogPath = Join-Path $RepoPath ".git\auto-commit.log"

function Write-Log {
    param([string]$Message)

    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
    Add-Content -Path $LogPath -Value "[$timestamp] $Message"
}

function Invoke-Git {
    param([string[]]$Arguments)

    $output = & git -C $RepoPath @Arguments 2>&1
    if ($LASTEXITCODE -ne 0) {
        throw "git $($Arguments -join ' ') failed: $($output | Out-String)"
    }

    return $output
}

function Commit-Changes {
    try {
        $status = & git -C $RepoPath status --porcelain 2>&1
        if ($LASTEXITCODE -ne 0) {
            throw "git status failed: $($status | Out-String)"
        }

        if ([string]::IsNullOrWhiteSpace(($status | Out-String))) {
            return
        }

        Invoke-Git @("add", "-A") | Out-Null

        & git -C $RepoPath diff --cached --quiet
        $diffExitCode = $LASTEXITCODE
        if ($diffExitCode -eq 0) {
            return
        }
        if ($diffExitCode -ne 1) {
            throw "git diff --cached --quiet failed with exit code $diffExitCode"
        }

        $message = "auto: snapshot $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')"
        Invoke-Git @("commit", "-m", $message) | Out-Null
        Write-Log "Committed changes: $message"

        if ($Push) {
            try {
                Invoke-Git @("push", $Remote, $Branch) | Out-Null
                Write-Log "Pushed to $Remote/$Branch"
            }
            catch {
                Write-Log "Push failed: $_"
            }
        }
    }
    catch {
        Write-Log "Commit failed: $_"
    }
}

Invoke-Git @("rev-parse", "--is-inside-work-tree") | Out-Null
Write-Log "Watcher started for $RepoPath"

$watcher = New-Object System.IO.FileSystemWatcher
$watcher.Path = $RepoPath
$watcher.IncludeSubdirectories = $true
$watcher.EnableRaisingEvents = $true

$pending = $false
$lastChange = Get-Date

Commit-Changes

while ($true) {
    $change = $watcher.WaitForChanged([System.IO.WatcherChangeTypes]::All, 1000)
    if (-not $change.TimedOut) {
        $pending = $true
        $lastChange = Get-Date
    }

    if ($pending -and ((Get-Date) - $lastChange).TotalSeconds -ge $DebounceSeconds) {
        Commit-Changes
        $pending = $false
    }
}
