# JavaLicense

JavaLicense uses poyrazinan/license-web-api for getting a result.

Basically getting JSON data from a Web API and this JSON data contains status of IP and Product Name. 
After then returns boolean about license status.

## Usage
```java
public static void main(String... args) {
    License license = new License.LicenseBuilder()
            .setProductName("Test")
            .setLink("https://poyrazinan.com.tr/api/plugin/check.php?plugin={product}&ip={ip}")
            .build();
    // You can check is something goes wrong with license query.
    // All you want to do is surrounding license.run() with try and catch
    // which can cause ConnectionFailureException.
    boolean status = license.run();
    if (!status) {
        // If license couldn't found.
        // Do something
        System.out.println("License couldn't found.");
    } else {
        // License found and valid.
        // Do something
        System.out.println("License found");
    }
}
```

## API
[![](https://jitpack.io/v/poyrazinan/JavaLicense.svg)](https://jitpack.io/#poyrazinan/JavaLicense)
[![Java CI with Maven](https://github.com/poyrazinan/JavaLicense/actions/workflows/maven.yml/badge.svg)](https://github.com/poyrazinan/JavaLicense/actions/workflows/maven.yml)
### Maven:
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.poyrazinan</groupId>
    <artifactId>JavaLicense</artifactId>
    <version>1.1</version>
    <scope>compile</scope>
  </dependency>
</dependencies>
```
### Gradle:
```
repositories {
  maven { url 'https://jitpack.io' }
}

dependencies {
  implementation 'com.github.poyrazinan:JavaLicense:1.1'
}
```

### Local Dependency
> You can find jar on releases.

## Requirements:

* Web API for storing licenses and check.
* User must have CURL for license check.

## Dependencies

- **JSON**
  - Version: 20210307
  - [Official Website](https://www.json.org/json-en.html)
- **Jetbrains Annotations**
  - Version: 22.0.0
  - [Github](https://github.com/JetBrains/java-annotations)

## Useful links:
* [Web API (php)](https://github.com/poyrazinan/license-web-api)
* [Discord Bot for Licenses](https://github.com/poyrazinan/GeikPlugins-Discord-Bot)
