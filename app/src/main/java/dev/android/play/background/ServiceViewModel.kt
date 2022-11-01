package dev.android.play.background

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewModelScope
import androidx.work.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.datastore.PreferencesKeys
import dev.android.play.utility.ObservableViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

@HiltViewModel
class ServiceViewModel @Inject constructor() : ObservableViewModel() {

    val TAG = "ServiceViewModel"

    @Bindable
    var nameObservable = ObservableField<String>("")
    private var name: String?
        get() = nameObservable.get()
        set(value) = nameObservable.set(value)

    @Bindable
    var mobileObservable = ObservableField<String>("")
    private var mobile: String?
        get() = mobileObservable.get()
        set(value) = mobileObservable.set(value)

    fun onStartForegroundService(view: View) {
        val intent = Intent(view.context, AppBindService::class.java)
        view.context.startForegroundService(intent)
    }

    fun startWorkManager(view: View) {

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val data = Data.Builder()
        data.putString("name", name)
        data.putString("mobile", mobile)

        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<AppWorker>()
                .setInputData(data.build())
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
//                .setConstraints(constraint.build())
                .build()

        val periodicSyncDataWork =
            PeriodicWorkRequest.Builder(AppWorker::class.java, 15, TimeUnit.MINUTES)
                .addTag("TAG_SYNC_WEATHER_DATA")
                .setConstraints(constraints) // setting a backoff on case the work needs to retry
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()

        WorkManager
            .getInstance(view.context)
            .enqueueUniquePeriodicWork(
                "TAG_SYNC_WEATHER_DATA",
                ExistingPeriodicWorkPolicy.REPLACE,  //Existing Periodic Work policy
                periodicSyncDataWork
            )
    }

    fun getDataSavedUsingWOrkManager(view: View) {

        val datastorePreference = view.context.dataStore.data

        val nameFlow: Flow<String> = datastorePreference.map {
            it[PreferencesKeys.NAME] ?: ""
        }

        val mobileFlow: Flow<String> = datastorePreference.map {
            it[PreferencesKeys.MOBILE] ?: ""
        }

        viewModelScope.launch {

            nameFlow.collectLatest {
                runBlocking {
                    Log.i(TAG, "Name Id $it")
                    name = it
                    Toast.makeText(view.context, "Data Fetched", Toast.LENGTH_SHORT).show()
                }
            }

            mobileFlow.collectLatest {
                runBlocking {
                    Log.i(TAG, "Mobile $it")
                    mobile = it
                    Toast.makeText(view.context, "Data Fetched", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}