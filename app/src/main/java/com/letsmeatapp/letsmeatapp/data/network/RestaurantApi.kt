package com.letsmeatapp.letsmeatapp.data.network

import com.letsmeatapp.letsmeatapp.data.responses.*
import retrofit2.Response
import retrofit2.http.*

interface RestaurantApi {
    @GET("restaurants")
    //suspend fun getRestaurants(): RestaurantResponse
    suspend fun getRestaurants(): Response<List<Restaurant>>

    @GET("restaurants/reviewcount")
    //suspend fun getRestaurants(): RestaurantResponse
    suspend fun getReviewNumbers(): Response<List<ReviewNumbers>>

    @FormUrlEncoded
    @POST("restaurants/create")
    suspend fun createRestaurant(
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("phone_number") phoneNumber: String,
        @Field("web_page") webUri: String,
        @Field("type") type: Int
    ) : RestaurantCreateResponse

    @GET("restaurants/show/{restaurantId}")
    suspend fun getRestaurantDetailsbyId(
        @Path("restaurantId") number: Int
    ): RestaurantDetailResponse
}