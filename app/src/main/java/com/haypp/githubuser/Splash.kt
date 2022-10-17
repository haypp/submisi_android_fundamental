package com.haypp.githubuser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.haypp.githubuser.activity.MainActivity
import com.haypp.githubuser.data.SettingPreferences
import com.haypp.githubuser.viewmodels.SettingViewModels
import com.haypp.githubuser.viewmodels.VMSettings

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val numb = 1000
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        val pref = SettingPreferences.getInstance(dataStore)
        val mViewModels = ViewModelProvider(this, VMSettings(pref))[SettingViewModels::class.java]
        mViewModels.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                moveToMain(numb)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                moveToMain(numb)
            }
        }
    }
    private fun moveToMain(numb: Int) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, numb.toLong())
    }

}