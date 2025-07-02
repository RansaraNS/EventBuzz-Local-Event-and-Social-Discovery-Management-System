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

:: Define full file path
set filepath=src\main\resources\db\changelog\changelogs\%filename%

:: Create changelogs folder if not exist
if not exist src\main\resources\db\changelog\changelogs (
    mkdir src\main\resources\db\changelog\changelogs
)

:: Tell user
echo Creating %filename% ...

:: Write XML content using a different approach
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

:: Done message
echo File created at: %filepath%
pause