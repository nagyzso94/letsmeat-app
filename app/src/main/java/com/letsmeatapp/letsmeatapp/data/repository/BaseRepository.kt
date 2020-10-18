package com.letsmeatapp.letsmeatapp.data.repository

import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.network.ReviewApi
import com.letsmeatapp.letsmeatapp.data.network.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO){
            try {
                Resource.Success(apiCall.invoke())
            } catch (e: Exception){
                when(e){
                    is HttpException -> {
                        Resource.Failure(false,e.code(),e.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true,null,null)
                    }
                }
            }
        }
    }

    suspend fun logout(api: UserApi) = safeApiCall {
        api.logout()
    }

}