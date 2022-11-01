# App Dependency Management
- https://proandroiddev.com/better-dependencies-management-using-buildsrc-kotlin-dsl-eda31cdb81bf
- mkdir buildSrc
- Now create build.gradle.kts file in the root folder (under buildSrc) 
- edit the above file
```
    plugins {
        `kotlin-dsl`
    }
    
    repositories {
        mavenCentral()
    }
```
- Now sync the project
- Now right click on buildSrc folder, select create new directory
- select src/main/kotlin
- Now create 3 files inside kotlin folder
  - 