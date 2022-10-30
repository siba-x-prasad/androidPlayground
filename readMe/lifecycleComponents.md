# Lifecycle Component
- MutableLiveData, ImmutableLiveDatta, Mediatorlivedata
- LiveData has no public method to modify its data.
- You can't update its value like setValue() or postValue()
- So when you don't want your data to be modified use LiveData If you want to modify your data later use MutableLiveData
- **MutableLiveData**
  - When it's required to change the content of a data source by using postValue or setValue
- **MediatorLivedata**
  - MediatorLiveData is a subclass of MutableLiveData made for the situation where you want to observe changes parallel from Multiple LiveData instances.
- Difference between Set Value and Post Value
  - **setValue()** Sets the value. If there are active observers, the value will be dispatched to them. This method must be called from the main thread.
  - **postValue()** Posts a task to a main thread to set the given value. If you called this method multiple times before a main thread executed a posted task, only the last value would be dispatched.
- Difference Between ViewModel and AndroidViewModel
- Difference Between LiveData and MutableLiveData
- MutableLiveData is commonly used since it provides the postValue() , setValue() methods publicly, something that LiveData class doesn't provide.
- LiveData/MutableLiveData is commonly used in updating data in a RecyclerView from a collection type(List, ArrayList etc).

## Difference between sharedFLow and StateFlow
- StateFlow takes a default value through the constructor and emits it immediately when someone starts collecting, 
- while a SharedFlow takes no value and emits nothing by default.
- https://developer.android.com/kotlin/flow/stateflow-and-sharedflow