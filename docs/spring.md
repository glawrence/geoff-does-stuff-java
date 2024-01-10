# Introduction
Spring is a powerful and large framework, or arguably many frameworks!

# Building Projects

## Gradle
There are times when working with Spring in a Gradle build is not as straightforward as you might hope.

One important principle is to let Spring do what it wants, after all, it is "opinionated". This means, let Spring pick the versions of dependencies where possible. A good place to get information on which dependencies Spring will do this for is [Dependency Versions](https://docs.spring.io/spring-boot/docs/current/reference/html/dependency-versions.html#appendix.dependency-versions) and this page also tells you the version.

Sometimes you want to know what version your build is using, you can do this, see [Dependency Management Plugin](https://docs.spring.io/dependency-management-plugin/docs/current/reference/html/#accessing-properties) for details, and information on overriding the version.

There is an example of access build versions below:

```groovy
test {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = 'full'
        events = ["skipped", "failed"]
    }
    doLast {
        println('Spring Dependency Versions')
        println("Spring Framework: ${dependencyManagement.importedProperties['spring-framework.version']}")
        println("Micrometer: ${dependencyManagement.importedProperties['micrometer.version']}")
        println("Micrometer (Tracing): ${dependencyManagement.importedProperties['micrometer-tracing.version']}")
    }
}
```
