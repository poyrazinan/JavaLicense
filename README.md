# JavaLicense | Protect your jar

JavaLicense uses poyrazinan/license-web-api for getting a result.

Basically getting JSON data from a Web API and this JSON data contains status of IP and Product Name. 
After then returns boolean about license status.

---

## Usage
```java
    public static void main(String... args) {
        License license = new LicenseBuilder()
                .setProductName("Test")
                .setLink("https://poyrazinan.com.tr/api/plugin/");
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

## Requirements:

* Web API for storing licenses and check.
* User must have CURL for license check.
* Developed on Java 16 (Can be usable lower versions probably.)

## Dependencies

- **JSON**
  - Version: 20210307
  - [Official Website](https://www.json.org/json-en.html)
- **Jetbrains Annotations**
  - Version: 22.0.0
  - [Github](https://github.com/JetBrains/java-annotations)

---

## Useful links:
* Web API (php)
* [Discord Bot for Licenses](https://github.com/poyrazinan/GeikPlugins-Discord-Bot)