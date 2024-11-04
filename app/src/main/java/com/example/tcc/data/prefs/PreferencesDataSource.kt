package com.example.tcc.data.prefs

import android.content.Context
import com.example.tcc.AppApplication

class PreferencesDataSource {

    private val prefs = AppApplication.getInstance().getSharedPreferences(
        SHARED_PREFS,
        Context.MODE_PRIVATE
    )

    fun putBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, fallbackValue: Boolean = false) =
        prefs.getBoolean(key, fallbackValue)

    companion object {
        private const val SHARED_PREFS = "tcc_shared_prefs"
        const val ITEMS_CREATED_ON_TABLES = "ITEMS_CREATED_ON_TABLES"
    }
}