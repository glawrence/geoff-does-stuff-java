# Gradle Notes
At present these are still rough notes, but they will be improved over time.

## Installing and Upgrading
Most of the time we use the Gradle Wrapper so that the Gradle version is controlled within the repository and also it is under version control. Upgrading the currently used Gradle Wrapper is easy and shown below. Although it is worth noting that this will not download the new binaries, this only happens on first use, but is easily triggered by simply passing the `--version` on its own.

### Windows
To install, see [Gradle | Installation](https://gradle.org/install/), which explains the manual steps.

To upgrade the wrapper use the following:

`gradlew wrapper --gradle-version=8.6`

### macOS
I would recommend installing Homebrew and then using that to install Gradle.

To upgrade the wrapper use the following:

`./gradlew wrapper --gradle-version=8.6`

## Dependency Versions
There is a lot of flexibility over which version is used, see [Declaring Versions and Ranges](https://docs.gradle.org/current/userguide/single_versions.html) for more details. I have had cause to use "+" as a version, within a library, which then meant the using module built find. Not quite sure why this worked, but it did and it seems sensible.