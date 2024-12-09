# Geoff Does Stuff (Java)
This is a Java project to accompany Geoff Does Stuff. Some things are easier to show in code with comments than to document with code snippets. The aim of this project is to show how some Java things work.

It is worth adding that [JUnit 5](https://junit.org/junit5/) is being used as well as [Gradle Build Tool](https://gradle.org/).

This project is primarily hosted on GitLab at [Geoff Lawrence / Geoff Does Stuff - Java](https://gitlab.com/glawrence/geoff-does-stuff-java) but is configured with [Repository Mirroring](https://docs.gitlab.com/ee/user/project/repository/repository_mirroring.html) to automatically mirror the project to [glawrence/geoff-does-stuff-java](https://github.com/glawrence/geoff-does-stuff-java) on GitHub.

## Executing
There are differences when working on different platforms, so they are covered separately. I have not tested this on Linux but the macOS instructions should work.

If when building Gradle does not find a suitable JDK, then have a look at `org.gradle.java.installations.paths` in the `gradle.properties` file.

The first and common step, is to change into the project directory on your local device.

### macOS
I hope to find a better way to do this, but for now, I am using this single command on macOS:

`./gradlew build && java -cp ./build/libs/geoff-does-stuff-java-1.0-SNAPSHOT.jar com.geoffdoesstuff.java.MainDemoApp`

After setting 'Main-Class' in the build.gradle file, inside jar/manifest/attributes you can simplify the above to this:

`./gradlew build && java -jar ./build/libs/geoff-does-stuff-java-1.0-SNAPSHOT.jar`

Alternatively to run a specific demo you can use the following:

`java -cp ./build/libs/geoff-does-stuff-java-1.0-SNAPSHOT.jar com.geoffdoesstuff.java.demo.Optionals`

After switching to the "application" plugin, instead of "java" and adding some config I was able to use the following, which is a simpler version of the original command above:

`./gradlew run --console plain`

The benefit here is that if necessary Gradle will recompile before running.

### Windows
I have been using these as two separate commands on Windows, so starting with

`gradlew build`

Followed by

`gradlew run --console plain`

or

`java -jar .\build\libs\geoff-does-stuff-java-1.0-SNAPSHOT.jar`

This will run the Demo App and display a menu, alternatively you can run a specific demo direct, as follows: 

`java -cp .\build\libs\geoff-does-stuff-java-1.0-SNAPSHOT.jar com.geoffdoesstuff.java.demo.Optionals`

However, if Java is not on the path then this works if JAVA_HOME is set:

`%JAVA_HOME%\bin\java -jar .\build\libs\geoff-does-stuff-java-1.0-SNAPSHOT.jar`

## Reading JavaDoc
When working on the JavaDoc you don't need to do a complete build by running everything including the unit tests. Here are some instructions for building the JavaDoc and then viewing it in the default browser.

### Windows

```
gradlew javadoc
start .\build\docs\javadoc\index.html
```

### macOS

`./gradlew javadoc && open ./build/docs/javadoc/index.html`