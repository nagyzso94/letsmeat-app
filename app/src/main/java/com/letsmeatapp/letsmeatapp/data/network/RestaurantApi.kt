package com.letsmeatapp.letsmeatapp.data.network

import com.letsmeatapp.letsmeatapp.data.responses.RestaurantResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RestaurantApi {
    @GET("restaurants")
    suspend fun getRestaurants(): RestaurantResponse

    @FormUrlEncoded
    @POST("restaurants/create")
    suspend fun createRestaurant(
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("phone_number") phoneNumber: String,
        @Field("web_page") webUri: String,
        @Field("type") type: Int
    ) : RestaurantResponse
    // TODO : Ez nem étterem listát fog kapni, kell majd egy fix, mielőtt csinálom azt a csoda hozzáadás screent!

}