package dev.android.play.customErrorHandling

import android.app.Application

class CrashApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalExceptionHandler.initialize(this, ErrorScreenActivity::class.java)
    }
}