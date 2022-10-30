package dev.android.play.customErrorHandling

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.Gson

class GlobalExceptionHandler private constructor(
    private val applicationContext: Context,
    private val defaultHandler: Thread.UncaughtExceptionHandler,
    private val activityToBeLaunched: Class<*>
) : Thread.UncaughtExceptionHandler {

    companion object {
        const val INTENT_DATA_NAME = "crash data"
        private const val TAG = "GlobalExceptionHandler"

        fun initialize(
            applicationContext: Context,
            activityToBeLaunched: Class<*>
        ) {
            val handler = GlobalExceptionHandler(
                applicationContext,
                Thread.getDefaultUncaughtExceptionHandler() as Thread.UncaughtExceptionHandler,
                activityToBeLaunched
            )
            Thread.setDefaultUncaughtExceptionHandler(handler)
        }

        fun getThrowableFromIntent(intent: Intent): Throwable? {
            return try {
                Gson().fromJson(intent.getStringExtra(INTENT_DATA_NAME), Throwable::class.java)
            } catch (e: Exception) {
                Log.e(TAG, "getThrowableFromIntent", e)
                null
            }
        }
    }


    override fun uncaughtException(t: Thread, e: Throwable) {
        try {
            launchActivity(applicationContext, activityToBeLaunched, e)
        } catch (e1: Exception) {
            defaultHandler.uncaughtException(t, e)
        }
    }

    private fun launchActivity(
        applicationContext: Context,
        activity: Class<*>,
        exception: Throwable
    ) {
        val crashIntent = Intent(applicationContext, activity).also {
            it.putExtra(INTENT_DATA_NAME, Gson().toJson(exception))
        }
        crashIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        crashIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        applicationContext.startActivity(crashIntent)
    }

}