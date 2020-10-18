package com.letsmeatapp.letsmeatapp.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    context: Context
) {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(name = "my_data_store")
    }

    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH]
        }

    suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    val userId: Flow<Int?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_USER]
        }

    suspend fun saveUserData(user: Int) {
        dataStore.edit { preferences ->
            preferences[KEY_USER] = user
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val KEY_AUTH = preferencesKey<String>(name = "key_auth")
        private val KEY_USER = preferencesKey<Int>(name = "key_user")
    }

}