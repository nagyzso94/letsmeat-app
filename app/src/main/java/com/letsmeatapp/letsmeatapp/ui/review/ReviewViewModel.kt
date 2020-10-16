package com.letsmeatapp.letsmeatapp.ui.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.repository.ReviewRepository
import com.letsmeatapp.letsmeatapp.data.responses.*
import com.letsmeatapp.letsmeatapp.ui.base.BaseViewModel
import com.letsmeatapp.letsmeatapp.ui.profile.ProfileViewModel
import kotlinx.coroutines.launch
import retrofit2.Response


class ReviewViewModel(
    private val repository: ReviewRepository
) : BaseViewModel(repository) {

    // Get reviews by restaurantId/UserId - they return list of restaurants
    private val _restaurantReviews: MutableLiveData<Resource<Response<List<ReviewResponseItem>>>> = MutableLiveData()
    val restaurantReviews: LiveData<Resource<Response<List<ReviewResponseItem>>>>
        get() = _restaurantReviews

    fun getReviewsbyRestaurantId(restaurantId: Int) = viewModelScope.launch {
        _restaurantReviews.value = repository.getReviewsbyRestaurantId(restaurantId)
    }

    fun getReviewsbyUserId(userId: Int) = viewModelScope.launch {
        _restaurantReviews.value = repository.getReviewsbyRestaurantId(userId)
    }

    // Retrieving created review from repository
    private val _reviewCreateResponse: MutableLiveData<Resource<ReviewCreationSuccess>> = MutableLiveData()
    val reviewCreateResponse: LiveData<Resource<ReviewCreationSuccess>>
        get() = _reviewCreateResponse

    fun createReview(
        user_id: Int,
        restaurant_id: Int,
        savouriness: Double,
        prices: Double,
        service: Double,
        cleanness: Double,
        other_aspect: String?
    ) = viewModelScope.launch {
        _reviewCreateResponse.value = repository.createReview(user_id,restaurant_id,savouriness,prices,service,cleanness,other_aspect)
    }

}