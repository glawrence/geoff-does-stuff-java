@ECHO OFF
SETLOCAL

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

SET /P MS_TIMESTAMP="Please enter MS Timestamp: "
SET /P ADD_DAYS="Please enter days to add, or press enter to skip: "

IF "%ADD_DAYS%"=="" (
	"%JAVA_HOME%\bin\java" -Dfile.encoding=UTF-8 -classpath %CLASSPATH% com.geoffdoesstuff.java.Conversions --ms-timestamp=%MS_TIMESTAMP%
) ELSE (
	"%JAVA_HOME%\bin\java" -Dfile.encoding=UTF-8 -classpath %CLASSPATH% com.geoffdoesstuff.java.Conversions --ms-timestamp=%MS_TIMESTAMP% --add-days=%ADD_DAYS%
)


ENDLOCAL