package com.letsmeatapp.letsmeatapp.data.network

import com.letsmeatapp.letsmeatapp.data.responses.LoginResponse
import com.letsmeatapp.letsmeatapp.data.responses.User
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("user")
    suspend fun getUser(): LoginResponse

    @POST("logout")
    suspend fun logout(): ResponseBody
}