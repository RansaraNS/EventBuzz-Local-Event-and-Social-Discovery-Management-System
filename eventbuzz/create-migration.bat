@echo off
:: Prompt for description
set /p desc="Enter change description (e.g., create-users-table): "

:: Get current date and time
for /f "tokens=2 delims==" %%I in ('"wmic os get localdatetime /value"') do set datetime=%%I
set year=%datetime:~0,4%
set month=%datetime:~4,2%
set day=%datetime:~6,2%
set hour=%datetime:~8,2%
set minute=%datetime:~10,2%
set second=%datetime:~12,2%
set filename=%year%_%month%_%day%_%hour%%minute%%second%_%desc%.xml

:: Define file paths
set filepath=src\main\resources\db\changelog\changelogs\%filename%
set masterfile=src\main\resources\db\changelog\db.changelog.master.xml
set tempfile=src\main\resources\db\changelog\temp_master.xml

:: Create changelogs folder if not exist
if not exist src\main\resources\db\changelog\changelogs (
    mkdir src\main\resources\db\changelog\changelogs
)

:: Tell user about file creation
echo Creating %filename% ...

:: Write XML content for the migration file
(
echo ^<?xml version="1.0" encoding="UTF-8"?^>
echo ^<databaseChangeLog
echo     xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
echo     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
echo     xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
echo     http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-latest.xsd">
echo.
echo    ^<!-- Add your changeSets here --^>
echo.
echo ^</databaseChangeLog^>
) > "%filepath%"

echo File created at: %filepath%

:: Now update the master changelog file
echo Adding entry to master changelog...

:: Check if master file exists
if not exist "%masterfile%" (
    echo Error: Master changelog file not found at %masterfile%
    pause
    exit /b 1
)

:: Method 1: Using simple text replacement
:: First, copy everything except the last line to temp file
type "%masterfile%" | findstr /v "</databaseChangeLog>" > "%tempfile%"

:: Add the new include line
echo     ^<include file="changelogs/%filename%"/^> >> "%tempfile%"

:: Add the closing tag back
echo. >> "%tempfile%"
echo ^</databaseChangeLog^> >> "%tempfile%"

:: Replace the original file
move "%tempfile%" "%masterfile%" > nul

echo.
echo ✓ Migration file created: %filename%
echo ✓ Added to master changelog: db.changelog.master.xml
echo.
pause