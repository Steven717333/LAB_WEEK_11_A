package com.example.lab_week_11_a.dataStore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// HARUS diletakkan di file yang sama, tapi di luar class
val Context.dataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(
    name = "settingsStore"
)

class SettingsStore(private val context: Context) {

    // Flow untuk membaca text
    val text: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_TEXT] ?: ""
        }

    // Simpan text ke DataStore
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TEXT] = text
        }
    }

    companion object {
        val KEY_TEXT = stringPreferencesKey("key_text")
    }
}
