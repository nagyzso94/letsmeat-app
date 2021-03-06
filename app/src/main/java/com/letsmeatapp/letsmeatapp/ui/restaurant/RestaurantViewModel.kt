package com.letsmeatapp.letsmeatapp.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.repository.RestaurantRepository
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.data.responses.RestaurantCreateResponse
import com.letsmeatapp.letsmeatapp.data.responses.RestaurantDetailResponse
import com.letsmeatapp.letsmeatapp.data.responses.ReviewNumbers
import com.letsmeatapp.letsmeatapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.Response

class RestaurantViewModel(
    private val repository: RestaurantRepository,
) : BaseViewModel(repository) {

    // Getting restaurants list from the repository
    private val _restaurants: MutableLiveData<Resource<Response<List<Restaurant>>>> =
        MutableLiveData()
    val restaurants: LiveData<Resource<Response<List<Restaurant>>>>
        get() = _restaurants

    fun getRestaurants() = viewModelScope.launch {
        _restaurants.value = repository.getRestaurants()
    }

    // Getting review numbers from the repository
    private val _reviewNumbers: MutableLiveData<Resource<Response<List<ReviewNumbers>>>> =
        MutableLiveData()
    val reviewNumbers: LiveData<Resource<Response<List<ReviewNumbers>>>>
        get() = _reviewNumbers

    fun getReviewNumbers() = viewModelScope.launch {
        _reviewNumbers.value = repository.getReviewNumbers()
    }

    // Retrieving created restaurant from the repository
    private val _restaurantCreateResponse: MutableLiveData<Resource<RestaurantCreateResponse>> =
        MutableLiveData()
    val restaurantCreateResponse: LiveData<Resource<RestaurantCreateResponse>>
        get() = _restaurantCreateResponse

    fun createRestaurant(
        name: String,
        address: String,
        phoneNumber: String,
        webUri: String,
        type: Int
    ) = viewModelScope.launch {
        _restaurantCreateResponse.value =
            repository.createRestaurant(name, address, phoneNumber, webUri, type)
    }

    // Get restaurant details with avg rating from the repository
    private val _restaurantDetails: MutableLiveData<Resource<RestaurantDetailResponse>> =
        MutableLiveData()
    val restaurantDetails: LiveData<Resource<RestaurantDetailResponse>>
        get() = _restaurantDetails

    fun getRestaurantDetailsById(id: Int) = viewModelScope.launch {
        _restaurantDetails.value = repository.getRestaurantDetailsbyId(id)
    }
}