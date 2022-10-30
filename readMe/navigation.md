# Navigation Library (Details)[https://developer.android.com/guide/navigation/navigation-getting-started]
- 

## Navigate to a destination

- 3 ways to navigate from one fragment to another.
    - Fragment.findNavController()
    - View.findNavController()
    - Activity.findNavController(viewId: Int)

```
    val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController
```

## Ensure type-safety by using Safe Args

```
    buildscript {
        repositories {
            google()
        }
        dependencies {
            def nav_version = "2.5.3"
            classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
        }
    }
```

```
    plugins {
      id 'androidx.navigation.safeargs'
    }
```

## How to pass data using Navigation

```
    override fun onClick(view: View) {
        val action =
            SpecifyAmountFragmentDirections
                .actionSpecifyAmountFragmentToConfirmationFragment()
        view.findNavController().navigate(action)
    }
```

## How to change the destination Fragment programmatically

```
    <fragment
        android:id="@+id/firstFragment"
        android:name="com.appname.package.FirstFragment" >
        <action
            android:id="@+id/action_firstFragment_to_secondFragment"
            app:destination="@id/secondFragment" /> 
    </fragment>
    
    <fragment
        android:id="@+id/secondFragment"
        android:name="com.appname.package.SecondFragment"/>
```

- Use NavOption to change

```
    NavOptions navOptions = new NavOptions.Builder()
        .setPopUpTo(R.id.firstFragment, true)
        .build();
```        

- And use them for the navigation:

```
    Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_secondFragment, bundle, navOptions);
```

## Change the default Destination Fragment Programmatically

```
val navHostFragment = nav_host_fragment as NavHostFragment
val graphInflater = navHostFragment.navController.navInflater
navGraph = graphInflater.inflate(R.navigation.nav_graph)
navController = navHostFragment.navController

val destination = if (intent.getBooleanExtra(
        IS_PRIVACY_POLICY_ACCEPTED,
        false
    )
) R.id.homeFragment else R.id.newPrivacyPolicyFragment
navGraph.startDestination = destination
navController.graph = navGraph
```