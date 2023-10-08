# Modularizing
## Important Links
- https://developer.android.com/topic/modularization/patterns
- https://android-developers.googleblog.com/2022/09/announcing-new-guide-to-android-app-modularization.html
- https://www.raywenderlich.com/books/real-world-android-by-tutorials/v1.0/chapters/8-multi-module-apps
- https://medium.com/@ahmedeelkhami/multi-module-architecture-in-android-5f76373a84a7
- How to avoid circular dependencies
- https://www.droidcon.com/2022/02/15/android-modularization-preps-things-to-know-before-modularizing-your-app/

- The multi-module architecture strategies show how you will organize the modules in your project. 
- There are two commonly used strategies. You will know the difference between them and which will be better to choose.
## Modularizing by Layer
- By putting each layer in a module, you will create a module for the presentation layer, another module for the domain layer and another for the data layer.
- Advantages
  - You got a clear separation of layers
  - You can determine which layer can access the other and which layer cannot.
- Disadvantages
  - You can’t reuse each module.
  - The modules will get crowded quickly since all your app’s code will be in each layer
## Modularizing by Feature
- Advantages
  - Easley accessing each feature.
  - Fewer conflicts when merging.
  - You can reuse these modules or replace them.
- Disadvantages
  - The separation between layers is not the best, as you can access the code from other layers.

## Which is better
- As you can see, each strategy has its advantages and disadvantages.
- so why don’t we mix and match these advantages while eliminating these disadvantages.
- So we can create a module for each feature, like Modularizing by Feature strategy, and then on each feature, 
- we will create three other modules, which are the three layers of clean architecture presentation, domain and data.
- In this approach, we have eliminated the disadvantages of both the above strategies while keeping the advantages of both of them together.


```
    //App dependencies in Build.gradle
    implementation project(path: ':feature1')
    implementation project(path: ':feature2')
    //Feature1 dependencies in Build.gradle
    implementation project(path: ':core')
    //Feature2 dependencies in Build.gradle
    implementation project(path: ':core')
    //Core dependencies in Build.gradle
    api project(path: ':location')
    api project(path: ':resources')
```
- **api :** dependencies which are transitively exported to consumers, for compile time and runtime.
- **implementation:** dependencies which are not meant to be exposed to consumers.

## Mistakes while Modularizing your project
- Modularizing too early or too quickly of your project. Don't create multi module from the beginning.
- Multi module is not even not required for lot of project
- Modularization by layer might create conflict between the developers
- 