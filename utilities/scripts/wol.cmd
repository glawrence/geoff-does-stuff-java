@ECHO OFF
SETLOCAL

SET IP_ADDRESS=192.168.1.1
SET MAC_ADDRESS=A1:B2:C3:D4:E5:F6

SET REPO_BASE=C:\git\geoff-does-stuff-java

SET LIBRARY_JAR=%REPO_BASE%\utilities\library\build\libs\library.jar
SET UTILS_JAR=%REPO_BASE%\utilities\utils\build\libs\utils.jar
SET CLASSPATH=%LIBRARY_JAR%;%UTILS_JAR%

IF NOT EXIST %LIBRARY_JAR% (
	ECHO Build missing, building now...
	CALL "%REPO_BASE%\gradlew" -p "%REPO_BASE%" build
)
IF NOT EXIST %UTILS_JAR% (
	ECHO Build missing, building now...
	CALL "%REPO_BASE%\gradlew" -p "%REPO_BASE%" build
)

IF "%1" == "u" (
	"%JAVA_HOME%\bin\java" -Dfile.encoding=UTF-8 -classpath %CLASSPATH% com.geoffdoesstuff.java.WakeOnLan --ip-address=%IP_ADDRESS% --mac-address=%MAC_ADDRESS%
	GOTO CONTINUE
)
IF "%1" == "d" (
	"%JAVA_HOME%\bin\java" -Dfile.encoding=UTF-8 -classpath %CLASSPATH% com.geoffdoesstuff.java.WakeOnLan --down --ip-address=%IP_ADDRESS%
	GOTO CONTINUE
)

ECHO ERROR: please specify u or d

:CONTINUE

ENDLOCAL