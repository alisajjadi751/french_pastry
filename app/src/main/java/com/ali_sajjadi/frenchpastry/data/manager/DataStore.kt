package com.ali_sajjadi.frenchpastry.data.manager

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore("user_preferences")

// Keys
private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
private val API_TOKEN_KEY = stringPreferencesKey("api_token")

class TokenManager private constructor(private val context: Context) {

    // ذخیره وضعیت لاگین
    suspend fun saveLoginStatus(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }

    // بازیابی وضعیت لاگین
    fun getLoginStatus(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }
    }

    // ذخیره توکن
    suspend fun saveToken(apiToken: String) {
        context.dataStore.edit { preferences ->
            preferences[API_TOKEN_KEY] = apiToken
        }
    }

    // بازیابی توکن
    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[API_TOKEN_KEY]
        }
    }

    // حذف اطلاعات (خروج از حساب)
    suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = false
            preferences.remove(API_TOKEN_KEY)
        }
    }

    companion object {
        // نگهداری از نمونه Singleton
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: TokenManager? = null

        // متد برای دریافت نمونه Singleton
        fun getInstance(context: Context): TokenManager {
            return INSTANCE ?: synchronized(this) {
                val instance = TokenManager(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }
}
