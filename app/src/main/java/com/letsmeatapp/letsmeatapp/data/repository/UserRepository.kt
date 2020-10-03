package com.letsmeatapp.letsmeatapp.data.repository

import com.letsmeatapp.letsmeatapp.data.UserPreferences
import com.letsmeatapp.letsmeatapp.data.network.AuthApi
import com.letsmeatapp.letsmeatapp.data.network.UserApi

class UserRepository(
    private val api : UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}


