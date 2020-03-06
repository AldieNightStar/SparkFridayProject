# SparkFridayProject
Spark FridayProject --> Using link shortener to monetize your apps (even localhost ;))

# Install

### Add repository
```xml
<repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
</repositories>
```

### Add dependency
```xml
<dependency>
    <groupId>com.github.AldieNightStar</groupId>
    <artifactId>SparkFridayProject</artifactId>
    <version>-SNAPSHOT</version>
</dependency>
```


# Usage
``` java
        // Java Spark service init
        Service service = Service.ignite().port(8080);

        // Create friday (You can do it once per project
        Friday friday = new Friday(service, "adf.ly", "10404431");
        // Inititalize the Friday Object
        friday.init("localhost:8080"); // Host, where your project hosted (ex: mysite.com)


        // Adding once-use url
        String url = friday.step(/* to wrap redirect for localhost */ true, (req, res) -> {
            return "xxx";
        }); // it will remove itself after click on that


        // Write out to console the url to go by browser to
        System.out.println(url);

```

Monetize your localhost server or even online game with our new FridayLib :)
