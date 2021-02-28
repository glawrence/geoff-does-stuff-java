# Geoff Does Stuff (Java)

This is a Java project to accompany Geoff Does Stuff. Some things are easier to show in code with comments than to document with code snippets. The aim of this project is to show how some Java things work.

It is worth adding that [JUnit 5](https://junit.org/junit5/) is being used as well as [Gradle Build Tool](https://gradle.org/).

## Executing

I hope to find a better way to do this, but for now, I am using this on macOS:

```./gradlew build && java -cp ./build/libs/geoff-does-stuff-java-1.0-SNAPSHOT.jar com.geoffdoesstuff.java.MainDemoApp```

After settings 'Main-Class' in the build.gradle file, inside jar/manifest/attributes you can simplify the above to this:

`./gradlew build && java -jar ./build/libs/geoff-does-stuff-java-1.0-SNAPSHOT.jar`