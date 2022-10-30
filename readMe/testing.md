# Testing

- https://developer.android.com/training/testing/fundamentals
-

## Unit Testing

- Unit Tests are generally written before writing the actual application.
- Unit Testing is done to ensure that the developer would be unable to write low quality/erroneous
  code.
- It makes sense to write Unit Tests before writing the actual app as then you would’t have a bias
  towards the success of your tests, you will write tests beforehand and the actual code will have
  to adhere to the design guidelines laid out by the test.

## JUnit methods:

- You encounter 3 new methods provided by JUnit Framework:
- **assertThat():** As is evident by the name, this method lets you create custom assertions and not
  just true and false values. It takes in 3 arguments. A reason/description, input value to be
  checked, expected actual value.
- **Is():** Is method returns a Matcher to match the source object to the one provided as the
  parameter of is();
- **equalTo():** As the name suggests, this method checks for equality between the expected and
  actual value.
- **When():** This is a very powerful method which takes in a method call as its parameter. It takes
  in the method call which is to be stubbed/duplicated. Once the method stub is executed, “then()”
  is called.
- **thenReturn():** It is called after the method stub provided in when() method has finished
  running. It is used to return the result of the method, if it is not void.

## Mockito

- Annotations
- Here you encounter three new annotations:
- **@Mock:** As explained above, it creates a dummy object needed by the class to be tested to
  function properly.
- **@Before:** This annotation is used to mark any method to run before executing the test cases.
  Here we initialize mSharedPreferenceEntry, mMockSharedPreferencesHelper,
  mMockBrokenSharedPreferencesHelper.
- **@RunWith:** It instructs the IDE to initialize the Mockito library. An alternative to this would
  be writing MockitoAnnotations.initMocks( testClass ) in the @Before method.