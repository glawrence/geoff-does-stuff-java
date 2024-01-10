# Gradle Notes
At present these are still rough notes, but they will be improved over time.

## Dependency Versions
There is a lot of flexibility over which version is used, see [Declaring Versions and Ranges](https://docs.gradle.org/current/userguide/single_versions.html) for more details. I have had cause to use "+" as a version, within a library, which then meant the using module built find. Not quite sure why this worked, but it did and it seems sensible.