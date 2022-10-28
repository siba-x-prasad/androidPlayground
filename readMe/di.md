# Dagger2 and Dagger Hint

# Dagger Hilt (Click here)[https://developer.android.com/training/dependency-injection/hilt-android]
- https://dagger.dev/hilt/


## Component (Click Here)[https://dagger.dev/hilt/components]
| Component                 | Injector for                    |     |
|---------------------------|---------------------------------|-----|
| SingletonComponent        | Application                     |     |
| ViewModelComponent        | ViewModel                       |     |
| ActivityComponent         | Activity                        |     |
| FragmentComponent         | Fragment                        |     |
| ViewComponent             | View                            |     |
| ViewWithFragmentComponent | View with @WithFragmentBindings |     |
| ServiceComponent          | Service                         |     |

 ### Life Cycle of components

| Component                 | Scope                   | Created at             | Destroyed at                     |
|---------------------------|-------------------------|------------------------|----------------------------------|
| SingletonComponent        | @Singleton              | Application#onCreate() | Application process is destroyed |
| ActivityRetainedComponent | @ActivityRetainedScoped | Activity#onCreate()1   | Activity#onDestroy()1            |
| ViewModelComponent        | @ViewModelScoped        | ViewModel created      | ViewModel destroyed              |
| ActivityComponent         | @ActivityScoped         | Activity#onCreate()    | Activity#onDestroy()             |
| FragmentComponent         | @FragmentScoped         | Fragment#onAttach()    | Fragment#onDestroy()             |
| ViewComponent             | @ViewScoped             | View#super()           | View destroyed                   |
| ViewWithFragmentComponent | @ViewScoped             | View#super()           | View destroyed                   |
| ServiceComponent          | @ServiceScoped          | Service#onCreate()     | Service#onDestroy()              |


## Scope
- When to scope?
- Scoping a binding has a cost on both the generated code size and its runtime performance so use scoping sparingly. 
- The general rule for determining if a binding should be scoped is to only scope the binding if it’s required for the correctness of the code. 
- If you think a binding should be scoped for purely performance reasons, first verify that the performance is an issue, and if it is consider using @Reusable instead of a component scope.
 
## Component default bindings
- Each Hilt component comes with a set of default bindings that can be injected as dependencies into your own custom bindings.

| Component                 | Default Bindings                      |
|---------------------------|---------------------------------------|
| SingletonComponent        | Application2                          |
| ActivityRetainedComponent | Application                           |
| ViewModelComponent        | SavedStateHandle, ViewModelLifecycle  |
| ActivityComponent         | Application, Activity                 |
| FragmentComponent         | Application, Activity, Fragment       |
| ViewComponent             | Application, Activity, View           |
| ViewWithFragmentComponent | Application, Activity, Fragment, View |
| ServiceComponent          | Application, Service                  |


- ActivityRetainedComponent lives across configuration changes, so it is created at the first onCreate and last onDestroy.
- The Application binding is available using either @ApplicationContext Context or Application.

## Hilt Application (Click Here)[https://dagger.dev/hilt/application]
- 


## Android Entry Point (Details)[https://dagger.dev/hilt/android-entry-point]
- Activity
- Fragment
- View
- Service
- BroadcastReceiver

- ViewModel is supported via @HiltViewModel
- Retained Fragments
- Calling setRetainInstance(true) in a Fragment’s onCreate method will keep a fragment instance across configuration changes (instead of destroying and recreating it).

## Views with Fragment bindings
- By default, only SingletonComponent and ActivityComponent bindings can be injected into the view. 
- To enable fragment bindings in your view, add the @WithFragmentBindings annotation to your class.

## Entry Points (Details)[https://dagger.dev/hilt/entry-points]
- 


## Custom Component (Details)[https://dagger.dev/hilt/custom-components]


## @Binds
- The @Binds annotation tells Hilt which implementation to use when it needs to provide an instance of an interface.
- The annotated function provides the following information to Hilt:
  - The function return type tells Hilt what interface the function provides instances of.
  - The function parameter tells Hilt which implementation to provide.
```
    interface AnalyticsService {
      fun analyticsMethods()
    }
    
    // Constructor-injected, because Hilt needs to know how to
    // provide instances of AnalyticsServiceImpl, too.
    class AnalyticsServiceImpl @Inject constructor(
      ...
    ) : AnalyticsService { ... }
    
    @Module
    @InstallIn(ActivityComponent::class)
    abstract class AnalyticsModule {
    
      @Binds
      abstract fun bindAnalyticsService(
        analyticsServiceImpl: AnalyticsServiceImpl
      ): AnalyticsService
    }
```
## Inject instances with @Provides
- Interfaces are not the only case where you cannot constructor-inject a type. 
- Constructor injection is also not possible if you don't own the class because it comes from an external library (classes like Retrofit, OkHttpClient, or Room databases), or if instances must be created with the builder pattern.
- Consider the previous example. If you don't directly own the AnalyticsService class, you can tell Hilt how to provide instances of this type by creating a function inside a Hilt module and annotating that function with @Provides.
- The annotated function supplies the following information to Hilt:
   - The function return type tells Hilt what type the function provides instances of.
   - The function parameters tell Hilt the dependencies of the corresponding type.
   - The function body tells Hilt how to provide an instance of the corresponding type. Hilt executes the function body every time it needs to provide an instance of that type.

```
    @Module
    @InstallIn(ActivityComponent::class)
    object AnalyticsModule {
    
      @Provides
      fun provideAnalyticsService(
        // Potential dependencies of this type
      ): AnalyticsService {
          return Retrofit.Builder()
                   .baseUrl("https://example.com")
                   .build()
                   .create(AnalyticsService::class.java)
      }
    }
```

## Provide multiple bindings for the same type
- In cases where you need Hilt to provide different implementations of the same type as dependencies, you must provide Hilt with multiple bindings. 
- You can define multiple bindings for the same type with qualifiers.
- A qualifier is an annotation that you use to identify a specific binding for a type when that type has multiple bindings defined.
- Consider the example. If you need to intercept calls to AnalyticsService, you could use an OkHttpClient object with an interceptor. 
- For other services, you might need to intercept calls in a different way. In that case, you need to tell Hilt how to provide two different implementations of OkHttpClient.
- First, define the qualifiers that you will use to annotate the @Binds or @Provides methods:
```
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient
```
	
	
	
	
	
	
	
	
	

