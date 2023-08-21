object AppConfig {
    const val compileSdk = 33
    const val minSdk = 28
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val buildToolsVersion = "29.0.3"
    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardConsumerRules = "consumer-rules.pro"
    const val dimension = "environment"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object DefaultConfig {
    val minSdk = 24
    val targetSdk = 29
    val compileSdk = 29
}

object AppModules {
    const val base = ":core:base"
    const val featureAccount = ":feature:account"
    const val featureHome = ":feature:home"
    const val featureProfile = ":feature:profile"
}