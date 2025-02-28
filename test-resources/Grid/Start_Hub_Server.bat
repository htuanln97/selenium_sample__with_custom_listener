cd /D %~dp0

title Start Selenium Hub Server

set GRID_PATH="..\..\Executables\selenium-server-standalone-3.141.59.jar"

java -jar %GRID_PATH% -role hub -port 9497 -maxSession 5

pause
