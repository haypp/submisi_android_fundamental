package com.haypp.githubuser

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.haypp.githubuser.data.SettingPreferences
import com.haypp.githubuser.databinding.ActivitySettingsBinding
import com.haypp.githubuser.viewmodels.SettingViewModels
import com.haypp.githubuser.viewmodels.VMSettings

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        mBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val mViewModels = ViewModelProvider(this, VMSettings(pref))[SettingViewModels::class.java]
        mViewModels.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                mBinding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                mBinding.switchTheme.isChecked = false
            }
        }
            mBinding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            mViewModels.saveThemeSetting(isChecked)
        }
    }
}