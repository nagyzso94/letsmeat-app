package com.letsmeatapp.letsmeatapp.data.network

import com.letsmeatapp.letsmeatapp.data.responses.RestaurantCreateResponse
import com.letsmeatapp.letsmeatapp.data.responses.RestaurantStatistics
import com.letsmeatapp.letsmeatapp.data.responses.Review
import com.letsmeatapp.letsmeatapp.data.responses.ReviewCreateResponse
import retrofit2.Response
import retrofit2.http.*

interface ReviewApi {
    @GET("reviews")
    suspend fun getReviews(): Response<List<Review>>

    @FormUrlEncoded
    @POST("reviews/create")
    suspend fun createReview(
        @Field("user_id") user_id: Int,
        @Field("restaurant_id") restaurant_id: Int,
        @Field("savouriness") savouriness: Int,
        @Field("prices") prices: Int,
        @Field("service") service: Int,
        @Field("cleanness") cleanness: Int,
        @Field("other_aspect") other_aspect: String?
    ) : ReviewCreateResponse
    // TODO ezt meg kell majd mahinálni, hogy rendes válasz jöjjön vissza és nem null errorbody
    // TODO a response sem oksa, az is todo

    @GET("reviews/{userId}")
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