@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM delete data if its there
echo Current directory: %cd%
if exist data.txt del data.txt

REM compile the code into the bin folder
javac  -cp src\main\java -Xlint:none -d ..\bin src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM Check if ACTUAL.TXT was created


REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Skynet < text-ui-test\input.txt > text-ui-test\ACTUAL.TXT

if not exist text-ui-test\ACTUAL.TXT (
    echo Error: ACTUAL.TXT was not created.
    exit /b 1
)

REM compare the output to the expected output
FC text-ui-test\ACTUAL.TXT text-ui-test\EXPECTED.TXT
