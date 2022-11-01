## Klint
```
Plugins {
    id “ord.jlleitschuh.gradle.klintt” version “”11.0.0”
    }
    
    Tasks.getByPath(“”preBuild).dependsOn(“ktlintFormat”)
    
    Klint{
        android = true
        ignoreFailures = false
        disabledRules = [“final-newline”, “no-wildcard-imports”]
        reporters{
            reporter “plain”
            reporter	“checkstyle”
            reporter “”sarif
        }
    }
    
    dependencies{
        …
    }
```
- Make any mistake and now run the below command in command line
```
./gradlew ktlinkCheck
```

- To fix all the issues
```
 ./gradlew ktlintFormat
```