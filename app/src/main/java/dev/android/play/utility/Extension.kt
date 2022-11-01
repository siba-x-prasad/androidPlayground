package dev.android.play.utility

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

class Extension {
    val USER_PREFERENCES_NAME = "user_preferences"
    val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )
}