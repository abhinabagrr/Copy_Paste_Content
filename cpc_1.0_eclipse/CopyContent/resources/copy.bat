@ECHO OFF
REM BFCPEOPTIONSTART
REM Advanced BAT to EXE Converter www.BatToExeConverter.com
REM BFCPEEXE=D:\github_tools\cpc_1.0\bin\copy.exe
REM BFCPEICON=C:\Program Files (x86)\Advanced BAT to EXE Converter v3.00\ab2econv300\icons\icon5.ico
REM BFCPEICONINDEX=1
REM BFCPEEMBEDDISPLAY=0
REM BFCPEEMBEDDELETE=1
REM BFCPEADMINEXE=0
REM BFCPEINVISEXE=1
REM BFCPEVERINCLUDE=1
REM BFCPEVERVERSION=1.0.0.0
REM BFCPEVERPRODUCT=CPC-Copy
REM BFCPEVERDESC=Copy Content of a file
REM BFCPEVERCOMPANY=Abhinaba Basu
REM BFCPEVERCOPYRIGHT=open source
REM BFCPEOPTIONEND
@ECHO ON
@echo off
echo %1
echo %2
pushd %1
call setup.bat
java -version

set CLASSPATH=%CLASSPATH%;..\lib\cpc.jar
java com.win.file.CopyContent %2 > ../copy.log

exit
