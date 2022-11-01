# Data Binding View Binding


## Data Binding [Click Here](https://developer.android.com/topic/libraries/data-binding)
- Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- add data binding to your app level gradle file
- Find the data binding sample [Here](https://github.com/android/databinding-samples)
```
buildFeatures{
    dataBinding = true
 }
```
- Your xml layout should looks like this
```

<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    
    <data>
        <variable
            name="viewmodel"
            type="com.myapp.data.ViewModel" />
    </data>
    
    // your layout code
</layout>    
```

- Two Data Binding [Click Here](https://developer.android.com/topic/libraries/data-binding/two-way)
- 

## View Binding [Click Here](https://developer.android.com/topic/libraries/view-binding)
- View binding is a feature that allows you to more easily write code that interacts with views. 
- Once view binding is enabled in a module, it generates a binding class for each XML layout file present in that module. 
- An instance of a binding class contains direct references to all views that have an ID in the corresponding layout.
- In most cases, view binding replaces findViewById.
```
android {
    ...
    buildFeatures {
        viewBinding true
    }
}
```

## Difference between view binding and Data Binding
- ViewBinding
  - Only binding views to code.

- DataBinding
- Binding data (from code) to views + ViewBinding (Binding views to code)
- There are three important differences
- With view binding, the layouts do not need a layout tag
- You can't use viewBinding to bind layouts with data in xml (No binding expressions, no BindingAdapters nor two-way binding with viewbinding)
- The main advantages of viewBinding are speed and efficiency. It has a shorter build time because it avoids the overhead and performance issues associated with databinding due to annotation processors affecting databinding's build time.