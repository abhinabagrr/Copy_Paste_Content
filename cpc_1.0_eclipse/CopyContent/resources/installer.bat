@echo off


set CLASSPATH=%CLASSPATH%;.\lib\cpc.jar
call java com.win.Installer -c > install.log
IF "%1"=="-m" GOTO end
call callcpx.bat
call java com.win.Installer -r > clean.log
:end

