package dev.android.play.background

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.AssistedInject
import dev.android.play.datastore.PreferencesKeys
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

@HiltWorker
class AppWorker @AssistedInject constructor(val context: Context, val workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    private val TAG = "AppWorker"

    override suspend fun doWork(): Result = coroutineScope {

        val name = inputData.getString("name")
        val mobile = inputData.getString("mobile")

        Log.i(TAG, "Name = $name Mobile = $mobile")

        saveDataToDataStore(context, name!!, mobile!!)

        val jobs = (0 until 100).map {
            async {
                //  downloadSynchronously("https://www.google.com")
            }
        }
        // awaitAll will throw an exception if a download fails, which CoroutineWorker will treat as a failure
        jobs.awaitAll()
        Result.success()
    }

    private suspend fun saveDataToDataStore(context: Context, name: String, mobile: String) {
        context.dataStore.edit {
            it[PreferencesKeys.NAME] = name
            it[PreferencesKeys.MOBILE] = mobile
        }
        Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show()
    }
}