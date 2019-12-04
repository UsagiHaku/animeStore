package com.example.data

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences("session", 0)

    fun saveSession() {
        preferences.edit().putBoolean("isLogged", true).apply()
    }

    fun isLogged(): Boolean {
        return preferences.getBoolean("isLogged", false)
    }

    fun logout() {
        preferences.edit().putBoolean("isLogged", false).apply()
    }
}
