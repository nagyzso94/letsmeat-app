package com.letsmeatapp.letsmeatapp.data.repository

import com.letsmeatapp.letsmeatapp.data.network.RestaurantApi

class RestaurantRepository(
    private val api: RestaurantApi
) : BaseRepository() {

    suspend fun getRestaurants() = safeApiCall {
        api.getRestaurants()
    }

    suspend fun getRestaurantDetailsbyId(id: Int) = safeApiCall {
        api.getRestaurantDetailsbyId(id)
    }

    suspend fun createRestaurant(
        name: String,
        address: String,
        phoneNumber: String,
        webUri: String,
        type: Int
    ) = safeApiCall {
        api.createRestaurant(name, address, phoneNumber, webUri, type)
    }
}