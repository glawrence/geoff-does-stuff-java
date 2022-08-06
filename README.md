# Geoff Does Stuff (Java)
This is a Java project to accompany Geoff Does Stuff. Some things are easier to show in code with comments than to document with code snippets. The aim of this project is to show how some Java things work.

It is worth adding that [JUnit 5](https://junit.org/junit5/) is being used as well as [Gradle Build Tool](https://gradle.org/).

This project is primarily hosted on GitLab at [Geoff Lawrence / Geoff Does Stuff - Java](https://gitlab.com/glawrence/geoff-does-stuff-java) but is configured with [Repository Mirroring](https://docs.gitlab.com/ee/user/project/repository/repository_mirroring.html) to automatically mirror the project to [glawrence/geoff-does-stuff-java](https://github.com/glawrence/geoff-does-stuff-java) on GitHub.

## Executing
There are differences when working on different platforms, so they are covered separately. I have not tested this on Linux but the macOS instructions should work.

The first, and common step, is to change into the project directory on your local device.

### macOS
I hope to find a better way to do this, but for now, I am using this single command on macOS:

`./gradlew build && java -cp ./build/libs/geoff-does-stuff-java-1.0-SNAPSHOT.jar com.geoffdoesstuff.java.MainDemoApp`

After settings 'Main-Class' in the build.gradle file, inside jar/manifest/attributes you can simplify the above to this:

`./gradlew build && java -jar ./build/libs/geoff-does-stuff-java-1.0-SNAPSHOT.jar`

### Windows
I have been using these as two separate commands on Windows, so starting with

`gradlew build`

Followed by

`java -jar .\build\libs\geoff-does-stuff-java-1.0-SNAPSHOT.jar`

However, if Java is not on the path then this works if JAVA_HOME is set:

`%JAVA_HOME%\bin\java -jar .\build\libs\geoff-does-stuff-java-1.0-SNAPSHOT.jar`
