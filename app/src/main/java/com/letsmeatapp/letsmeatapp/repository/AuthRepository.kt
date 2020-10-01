package com.letsmeatapp.letsmeatapp.repository

import com.letsmeatapp.letsmeatapp.network.AuthApi

class AuthRepository(
    private val api : AuthApi
) : BaseRepository() {
    suspend fun login(email: String,password: String) = safeApiCall {
        api.login(email,password)
    }
}