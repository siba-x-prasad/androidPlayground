package dev.android.play.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val IS_MALE = booleanPreferencesKey("isMale")
    val EMAIL_ID = stringPreferencesKey("emailId")
    val PASSWORD = stringPreferencesKey("password")


    const val USER_STORE_NAME = "user_stores"
    const val DATA_STORE_FILE_NAME = "user_store.pb"
    const val SORT_ORDER_KEY = "sort_order"

}