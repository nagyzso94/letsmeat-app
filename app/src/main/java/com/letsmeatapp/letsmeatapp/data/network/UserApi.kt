package com.letsmeatapp.letsmeatapp.data.network

import com.letsmeatapp.letsmeatapp.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {
    @GET("user")
    suspend fun getUser(): LoginResponse
}