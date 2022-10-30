package dev.android.play.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AndroidPlayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}