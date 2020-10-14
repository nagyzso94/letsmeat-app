package com.letsmeatapp.letsmeatapp.data.repository

import com.letsmeatapp.letsmeatapp.data.network.UserApi

class UserRepository(
    private val api : UserApi
) : BaseRepository() {
    // todo : kell ez ide? ha baserepository-ban implementálom, akkor ez már dupla :D
    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}


