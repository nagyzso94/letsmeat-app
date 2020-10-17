package com.letsmeatapp.letsmeatapp.data.network

import com.letsmeatapp.letsmeatapp.data.responses.*
import retrofit2.Response
import retrofit2.http.*

interface ReviewApi {
    @FormUrlEncoded
    @POST("reviews/create")
    suspend fun createReview(
        @Field("user_id") user_id: Int,
        @Field("restaurant_id") restaurant_id: Int,
        @Field("savouriness") savouriness: Double,
        @Field("prices") prices: Double,
        @Field("service") service: Double,
        @Field("cleanness") cleanness: Double,
        @Field("other_aspect") other_aspect: String
    ) : ReviewCreateResponse

    @GET("reviews/show/user/{userId}")
    suspend fun getReviewsbyUserId(
        @Path("userId") number: Int
    ): Response<List<Review>>

    @GET("reviews/show/{restaurantId}")
    suspend fun getReviewsbyRestaurantId(
        @Path("restaurantId") number: Int
    ): Response<List<Review>>

    @GET("reviews/statistics/{restaurantId}")
    suspend fun getStatistics(
        @Path("restaurantId") number: Int
    ): Response<RestaurantStatistics>


}