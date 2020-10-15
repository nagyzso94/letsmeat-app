package com.letsmeatapp.letsmeatapp.data.repository

import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.network.RestaurantApi
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
            } catch (throwable: Throwable){
                when(throwable){
                    is HttpException -> {
                        Resource.Failure(false,throwable.code(),throwable.response()?.errorBody())
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

    suspend fun getRestaurantStatistics(api: ReviewApi, restaurant_id: Int) = safeApiCall {
        api.getReviewsbyRestaurantId(restaurant_id)
    }

    suspend fun getUser(api: UserApi) = safeApiCall {
        api.getUser()
    }
}