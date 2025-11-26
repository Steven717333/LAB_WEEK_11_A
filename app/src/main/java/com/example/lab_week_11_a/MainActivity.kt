package com.example.lab_week_11_a

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_11_a.dataStore.SettingsApplication
import com.example.lab_week_11_a.dataStore.SettingsViewModel
import com.example.lab_week_11_a.sharedPreference.PreferenceApplication
import com.example.lab_week_11_a.sharedPreference.PreferenceViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ambil PreferenceWrapper dari Application
//        val preferenceWrapper =
//            (application as PreferenceApplication).preferenceWrapper
//
//        // Buat ViewModel menggunakan custom factory
//        val preferenceViewModel = ViewModelProvider(
//            this,
//            object : ViewModelProvider.Factory {
//                override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                    return PreferenceViewModel(preferenceWrapper) as T
//                }
//            }
//        )[PreferenceViewModel::class.java]
//
//        // Observe LiveData dari PreferenceViewModel
//        preferenceViewModel.getText().observe(this) { text ->
//            findViewById<TextView>(R.id.activity_main_text_view).text = text
//        }

        val preferenceWrapper =
            (application as SettingsApplication).settingsStore

        // Buat ViewModel menggunakan custom factory
        val preferenceViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SettingsViewModel(preferenceWrapper) as T
                }
            }
        )[SettingsViewModel::class.java]

        // Observe LiveData dari PreferenceViewModel
        preferenceViewModel.textLiveData.observe(this) {
            findViewById<TextView>(
                R.id.activity_main_text_view
            ).text = it
        }

        // Button: Save ke SharedPreferences
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            val inputText =
                findViewById<EditText>(R.id.activity_main_edit_text).text.toString()

            preferenceViewModel.saveText(inputText)
        }
    }
}
