package com.letsmeatapp.letsmeatapp.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.repository.RestaurantRepository
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.data.responses.RestaurantResponse
import com.letsmeatapp.letsmeatapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.Response

class RestaurantViewModel(
    private val repository: RestaurantRepository
) : BaseViewModel(repository) {

    // Getting restaurants list from the repository
    private val _restaurants: MutableLiveData<Resource<Response<List<Restaurant>>>> = MutableLiveData()
    val restaurants: LiveData<Resource<Response<List<Restaurant>>>>
        get() = _restaurants

    fun getRestaurants() = viewModelScope.launch {
        _restaurants.value = repository.getRestaurants()
    } 

    // Retrieving created restaurant from the repository
    private val _restaurantCreateResponse: MutableLiveData<Resource<RestaurantResponse>> = MutableLiveData()
    val restaurantCreateResponse: LiveData<Resource<RestaurantResponse>>
        get() = _restaurantCreateResponse

    fun createRestaurant(
        name: String,
        address: String,
        phoneNumber: String,
        webUri: String,
        type: Int
    ) = viewModelScope.launch {
        _restaurantCreateResponse.value = repository.createRestaurant(name,address,phoneNumber,webUri,type)
    }
}