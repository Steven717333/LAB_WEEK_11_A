package com.example.lab_week_11_a.dataStore;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsStore: SettingsStore) : ViewModel() {

    private val _textLiveData = MutableLiveData<String>()
    val textLiveData: LiveData<String> = _textLiveData

    init {
        // Collect data store secara asynchronous
        viewModelScope.launch {
            settingsStore.text.collectLatest { value ->
                _textLiveData.postValue(value)
            }
        }
    }

    fun saveText(text: String) {
        // Simpan data secara asynchronous
        viewModelScope.launch {
            settingsStore.saveText(text)
        }
    }
}
