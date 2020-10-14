package com.letsmeatapp.letsmeatapp.data.repository

import com.letsmeatapp.letsmeatapp.data.network.ReviewApi
import retrofit2.http.Field

class ReviewRepository(
    private val api : ReviewApi
) : BaseRepository() {

    suspend fun getReviewsbyUserId(userId: Int) = safeApiCall {
        api.getReviewsbyUserId(userId)
    }

    suspend fun getReviewsbyRestaurantId(restaurantId: Int) = safeApiCall {
        api.getReviewsbyRestaurantId(restaurantId)
    }

    suspend fun createReview(
        user_id: Int,
        restaurant_id: Int,
        savouriness: Int,
        prices: Int,
        service: Int,
        cleanness: Int,
        other_aspect: String?
    ) = safeApiCall {
        api.createReview(user_id,restaurant_id,savouriness,prices,service,cleanness, other_aspect)
    }


    // todo törlés?
    /*suspend fun getRestaurantStatistics(
        restaurant_id: Int
    ) = safeApiCall {
        api.getReviewsbyRestaurantId(restaurant_id)
    }*/
}


