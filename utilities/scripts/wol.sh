#!/bin/bash

# this is written as a single function, because I like to drop functions like this in my profile file, which makes
# the "wol" command available everywhere, without having to be on the path

function wol() {
    IP_ADDRESS=192.168.1.1
    MAC_ADDRESS=A1:B2:C3:D4:E5:F6

    REPO_BASE=~/git/geoff-does-stuff-java

    LIBRARY_JAR=$REPO_BASE/utilities/library/build/libs/library.jar
    UTILS_JAR=$REPO_BASE/utilities/utils/build/libs/utils.jar
    CLASSPATH=$LIBRARY_JAR:$UTILS_JAR

    if [[ ! -f "$LIBRARY_JAR" ]] || [[ ! -f "$UTILS_JAR" ]]; then
        echo "Build missing, building now..."
        $REPO_BASE/gradlew -p $REPO_BASE build
    fi

    if [[ "$1" == "d" ]]; then
        $JAVA_HOME/bin/java -Dfile.encoding=UTF-8 -classpath $CLASSPATH com.geoffdoesstuff.java.WakeOnLan --ip-address=$IP_ADDRESS --down
    elif [[ "$1" == "u" ]]; then
        $JAVA_HOME/bin/java -Dfile.encoding=UTF-8 -classpath $CLASSPATH com.geoffdoesstuff.java.WakeOnLan --mac-address=$MAC_ADDRESS --ip-address=$IP_ADDRESS
    else
        echo "ERROR: please specify '$0 u' or '$0 d' for up or down respectively"
    fi
}

wol "$1"
