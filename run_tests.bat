@echo off
setlocal enabledelayedexpansion

title Test Runner
color 0A

:menu
cls
echo ====================================
echo          Test Runner
echo ====================================
echo 1. Run PurchaseOrder test
echo 2. Exit
echo ====================================
set /p choice=Choose an option (1-2): 

if "%choice%"=="1" set "profile=PurchaseOrder"
if "%choice%"=="2" goto exit

if not defined profile (
    echo Invalid option. Please choose a number between 1 and 2.
    pause
    goto menu
)

echo Running mvn test -P%profile%...
start cmd /k "mvn test -Dsurefire.suiteXmlFiles=%profile%Test.xml && pause && exit"
echo Command started in a new window. Please wait for it to finish executing.
pause
set "profile="
goto menu

:exit
echo Exiting...
pause
exit