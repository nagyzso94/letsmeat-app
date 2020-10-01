package com.letsmeatapp.letsmeatapp.network

import okhttp3.ResponseBody

sealed class Resource<out T> {

    data class Success<out T>(val value : T) : Resource<T>()

    data class Failure(
        val isNetworkError: Boolean?,
        val errCode : Int?,
        val errorBody : ResponseBody?
    ) : Resource<Nothing>()

}