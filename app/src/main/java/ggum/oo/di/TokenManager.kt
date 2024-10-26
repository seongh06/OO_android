package ggum.oo.di

import android.content.Context
import android.content.SharedPreferences

object TokenManager {
    private const val PREFS_NAME = "prefs"
    private const val ACCESS_TOKEN = "Access_Token"

    private lateinit var prefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = prefs.edit()
    }

    fun setAccessToken(token: String) {
        editor.putString(ACCESS_TOKEN, token).apply()
    }

    fun getAccessToken(): String? {
        return prefs.getString(ACCESS_TOKEN, null)
    }

    fun clearToken() {
        editor.clear().apply()
    }
}
