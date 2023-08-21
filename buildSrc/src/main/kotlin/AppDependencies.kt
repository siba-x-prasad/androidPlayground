import org.gradle.api.artifacts.dsl.DependencyHandler

object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradle}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

object Modules {
    val app = ":app"
    val core = ":core"
    val jetpack = ":jetpack"
    val helper = ":helper"
    val network = ":network"
    val utility = ":utility"
}

object Version {
    //app level
    const val compileVersion = 33
    const val gradle = "4.0.1"
    const val kotlin = "1.4.0"
    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"

    // libs
    const val coreKtx = "1.8.0"
    const val appcompat = "1.6.1"
    const val androidMaterial = "1.6.1"
    const val constraintLayout = "2.1.4"
    const val lifecycle_version = "2.4.0"
    const val navigationFragmentKtx = "2.3.5"
    const val navigationUi = "2.3.5"

    //test
    const val junit = "4.13.2"
    const val extJunit = "1.1.1"
    const val espresso = "3.2.0"

    // compose version
    const val composeVersion = "1.2.0"
    // room database
    const val roomVerions= "2.4.2"
    const val coroutine= "1.3.7"
    const val workManager= "2.7.1"
    val paging = "3.1.1"

    const val activity = "1.7.2"
}

object AppDependencies {

    // Activity
    // Java language implementation
    const val activity = "androidx.activity:activity:${Version.activity}"
    const val activityKtx = "androidx.activity:activity-ktx:${Version.activity}"
    // ads
    const val adsIdentifier = "androidx.ads:ads-identifier:1.0.0-alpha05"
    const val guava = "com.google.guava:guava:28.0-android"
    // annotation
    const val annotation = "androidx.annotation:annotation:1.6.0"
    // To use the Java-compatible @Experimental API annotation
    const val annotationExperimental = "androidx.annotation:annotation-experimental:1.3.1"


    /************  BASIC DEPENDENCIES ***************/
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val androidMaterial = "com.google.android.material:material:${Version.androidMaterial}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"

    //test libs
    // testImplementation
    const val junit = "junit:junit:${Versions.junit}"

    // androidTestImplementation
    const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"

    // androidTestImplementation
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    //std lib

    val lifecycleViewmodel =  "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle_version}"
    val lifecycleViewmodelKtx =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"


    // Android Paging Library
    val paging = "androidx.paging:paging-runtime:${Versions.paging}"

    // Viewpager2
    val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0"

    // jetpack compose
    const val composeUI = "androidx.compose.ui:ui:${Versions.composeVersion}"
    val composeMaterial  =  "androidx.compose.material3:material3:1.0.0-alpha01"
    val composePreview =  "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    val composeActivity =  "androidx.activity:activity-compose:1.5.1"
    val composeRuntime = "androidx.compose.runtime:runtime:${Versions.composeVersion}"
    val composeFoundation = "androidx.compose.foundation:foundation:${Versions.composeVersion}"
    val composeLayout = "androidx.compose.foundation:foundation-layout:${Versions.composeVersion}"
    val composeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"


    //     val  composeAdapter =  "com.google.android.material:compose-theme-adapter:${Versions.composeVersion}"
    val composeUiFramework = "androidx.ui:ui-framework:0.1.0-dev03"


    // dagger hilt
    // https://developer.android.com/training/dependency-injection/hilt-android
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val kaptDaggerHilt = "com.google.dagger:hilt-compiler:${Versions.hilt}"


    //For InstantTaskExecutorRule
    val coreTestImplementation = "androidx.arch.core:core-testing:2.1.0"
    val mockitoImplementation = "org.mockito:mockito-inline:2.13.0"

    // RX Java3
    val rxAndroid2 = "io.reactivex.rxjava2:rxandroid:2.0.1"
    val rxJava2 = "io.reactivex.rxjava2:rxjava:2.1.9"


    // Jetpack Navigation
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentKtx}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUi}"

    val coreTestImplementations = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    /****************  DATA STORAGE  ******************/
    const val datastore = "androidx.datastore:datastore-preferences:1.0.0"
    const val datastoreProto = "androidx.datastore:datastore:1.0.0"


    /******************    ROOM DATABASE     ********************/
    const val roomDatabaseRuntime =  "androidx.room:room-runtime:${Versions.roomVerion}"
    // annotationProcessor
    const val roomDatabaseCompiler =  "androidx.room:room-compiler:${Versions.roomVerion}"
    // To use Kotlin annotation processing tool (kapt)
    //    kapt
    const val kaptRoomCompiler =  "androidx.room:room-compiler:${Versions.roomVerion}"
    // To use Kotlin Symbol Processing (KSP)
    // ksp
    const val kspRoomCompiler =  "androidx.room:room-compiler:${Versions.roomVerion}"


    /********************   WORK MANAGER   *********************/
    // https://developer.android.com/jetpack/androidx/releases/work
    val workManager = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    val composeUITooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"


    /**************************NETWORK**************************/
    // Retrofit
    val retrofit2 = "com.squareup.retrofit2:retrofit:2.9.0"
    val okhttp3 = "com.squareup.okhttp3:okhttp:4.7.2"
    val intercepter = "com.squareup.okhttp3:logging-interceptor:4.7.2"
    val converter = "com.squareup.retrofit2:converter-gson:2.9.0"

    // Ktor
    val ktorCore = "io.ktor:ktor-server-core:2.1.2"
    val ktorServer = "io.ktor:ktor-server-netty:2.1.2"


    /*********************** IMAGE LOADING ****************/
    const val picasso = "com.squareup.picasso:picasso:2.5.2"
    const val glide = "com.github.bumptech.glide:glide:4.12.0"
    // Glide v4 uses this new annotation processor -- see https://bumptech.github.io/glide/doc/generatedapi.html
    // annotationProcessor
    const val glideImpl = "com.github.bumptech.glide:compiler:4.12.0"

    // coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutine}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}"

    // testing
    //For runBlockingTest, CoroutineDispatcher etc. testImplementation
    val coroutineTestImplementation = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2"

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: String) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.projectImplementation(list: String) {
    add("implementation", list)
}

fun DependencyHandler.implementation(list: String) {
    add("implementation", list)
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}