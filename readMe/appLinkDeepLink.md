# App Link Deep Link

## Deep Link
- Deep Links are a concept that help users navigate between the web and applications. 
- They are basically URLs which navigate users directly to the specific content in applications.
- To enable deep linking, add
```
        <intent-filter android:autoVerify="true">
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <!-- If a user clicks on a shared link that uses the "http" scheme, your
                     app should be able to delegate that traffic to "https". -->
                <data android:scheme="http" />
                <data android:scheme="https" />
                <!-- Include one or more domains that should be verified. -->
                <data android:host="www.spcart.com"
                    android:pathPrefix="/product"
                    />
                <data android:host="mobile.spcart.com"
                    android:pathPrefix="/mycart"
                    />
            </intent-filter>
```
## App Link
- On the other hand, Android App Links allow an app to designate itself as the default handler of application domain or URL. 
- Unfortunately It works only on on Android 6.0 (API level 23) and higher.
- Digital Asset Links JSON
- (To App Link generator) [https://developers.google.com/digital-asset-links/tools/generator] 
- https://blog.branch.io/technical-guide-to-android-app-links/#:~:text=The%20Android%20App%20Link%20is,when%20the%20link%20is%20clicked.
- 