@echo off
echo %1
pushd %1
call setup.bat
java -version

set CLASSPATH=%CLASSPATH%;..\lib\cpc.jar
javaw com.win.file.PasteContent %2 > ../paste.log

rem pause
