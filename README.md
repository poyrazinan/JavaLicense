# JavaLicense

JavaLicense is a license validation and management solution for your Java projects. It enables you to check, validate, and manage licenses within your Java applications.

## Usage
```java
public static void main(String[] args) {
  try {
    // License object creation
    License license = new License("dailyreward", "https://geik.xyz/app/api/license2.php?product={product}");
    // License check
    License.LicenseResponse status = license.run();
    // Checks if server has license or not
    if (status.isHasLicense()) {
      // Code block for license found situation
      System.out.println("License found!");
    }
    else
      // Code block for license couldn't be found situation
      System.out.println("License couldn't be found");

    // Exceptions
  } catch (ConnectionFailureException e) {
    // When connection fails, throws this exception
    System.out.println("Connection failed to remote server.");
    // When response not equal to 500, throws this exception
  } catch (ResponseCodeException e) {
    System.out.println("Response code is not correct, check api server!");
  } catch (IOException e) {
    throw new RuntimeException(e);
  }
}
```

## API
[![](https://jitpack.io/v/poyrazinan/JavaLicense.svg)](https://jitpack.io/#poyrazinan/JavaLicense)
[![Java CI with Maven](https://github.com/poyrazinan/JavaLicense/actions/workflows/maven.yml/badge.svg)](https://github.com/poyrazinan/JavaLicense/actions/workflows/maven.yml)
[![JavaLicense JavaDoc](https://github.com/poyrazinan/JavaLicense/actions/workflows/publish-javadoc-maven.yml/badge.svg)](https://github.com/poyrazinan/JavaLicense/actions/workflows/publish-javadoc-maven.yml)

### Javadoc
[Click](https://poyrazinan.github.io/JavaLicense/javadoc/)

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
    <version>1.4</version>
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
  implementation 'com.github.poyrazinan:JavaLicense:1.4'
}
```

### Local Dependency
> You can find jar on releases.

## Requirements:

* Web API for storing licenses and check. [Suggest you my api](https://github.com/poyrazinan/license-web-api)

## Contributions

- If you'd like to provide feedback or contribute to the project, please open an issue on the GitHub repository or submit a pull request.


## Dependencies

- **JSON**
  - Version: 20231013
  - [Official Website](https://www.json.org/json-en.html)
- **Jetbrains Annotations**
  - Version: RELEASE
  - [Github](https://github.com/JetBrains/java-annotations)
- **Lombok**
  - Version: 1.18.26
  - [Official Website](https://projectlombok.org/)

## Useful links:
* [Web API (php)](https://github.com/poyrazinan/license-web-api)
* [Discord Bot for Licenses](https://github.com/poyrazinan/GeikPlugins-Discord-Bot)

## License

This project is licensed under the MIT License. For more information, please see the [LICENSE](LICENSE) file.
