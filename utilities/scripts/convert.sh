#!/bin/bash

REPO_BASE=~/git/geoff-does-stuff-java

LIBRARY_JAR=$REPO_BASE/utilities/library/build/libs/library.jar
UTILS_JAR=$REPO_BASE/utilities/utils/build/libs/utils.jar
CLASSPATH=$LIBRARY_JAR:$UTILS_JAR

if [[ ! -f "$LIBRARY_JAR" ]] || [[ ! -f "$UTILS_JAR" ]]; then
  echo "Build missing, building now..."
  $REPO_BASE/gradlew -p $REPO_BASE build
fi

read -r -p 'Please enter MS Timestamp: ' MS_TIMESTAMP
read -r -p 'Please enter days to add, or press enter to skip: ' ADD_DAYS

if [[ "$ADD_DAYS" == "" ]]; then
  $JAVA_HOME/bin/java -Dfile.encoding=UTF-8 -classpath $CLASSPATH com.geoffdoesstuff.java.Conversions --ms-timestamp=$MS_TIMESTAMP
else
  $JAVA_HOME/bin/java -Dfile.encoding=UTF-8 -classpath $CLASSPATH com.geoffdoesstuff.java.Conversions --ms-timestamp=$MS_TIMESTAMP --add-days=$ADD_DAYS
fi
