package com.letsmeatapp.letsmeatapp.data.network

import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.data.responses.RestaurantCreateResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RestaurantApi {
    @GET("restaurants")
    //suspend fun getRestaurants(): RestaurantResponse
    suspend fun getRestaurants(): Response<List<Restaurant>>

    @FormUrlEncoded
    @POST("restaurants/create")
    suspend fun createRestaurant(
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("phone_number") phoneNumber: String,
        @Field("web_page") webUri: String,
        @Field("type") type: Int
    ) : RestaurantCreateResponse
}