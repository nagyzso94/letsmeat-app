package com.letsmeatapp.letsmeatapp.data.repository

import com.letsmeatapp.letsmeatapp.data.UserPreferences
import com.letsmeatapp.letsmeatapp.data.network.AuthApi

class AuthRepository(
    private val api : AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(email: String,password: String) = safeApiCall {
        api.login(email,password)
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }

    suspend fun register(name: String, email: String, password: String, passwordConfirmation: String) = safeApiCall {
        api.register(name,email,password,passwordConfirmation)
    }
}


