package dev.ptit.setup.pref

import android.content.Context
import dev.ptit.setup.keys.Keys

class LazyPref(private val context: Context) {

    fun get(key: String, defaultValue: String): String {
        val sharedPreferences =
            context.getSharedPreferences(Keys.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun put(key: String, value: String) {
        val sharedPreferences =
            context.getSharedPreferences(Keys.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun get(key: String, defaultValue: Boolean): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(Keys.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun put(key: String, value: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(Keys.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }


}