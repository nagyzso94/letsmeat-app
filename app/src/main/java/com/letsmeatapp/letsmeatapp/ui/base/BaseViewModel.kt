package com.letsmeatapp.letsmeatapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letsmeatapp.letsmeatapp.data.network.RestaurantApi
import com.letsmeatapp.letsmeatapp.data.network.ReviewApi
import com.letsmeatapp.letsmeatapp.data.network.UserApi
import com.letsmeatapp.letsmeatapp.data.repository.BaseRepository
import com.letsmeatapp.letsmeatapp.data.responses.Review
import com.letsmeatapp.letsmeatapp.data.responses.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel (
    private val repository: BaseRepository
): ViewModel(){

    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }
    suspend fun getRestaurantStatistics(api: ReviewApi, restaurant_id: Int) = withContext(Dispatchers.IO) { repository.getRestaurantStatistics(api,restaurant_id) }
    suspend fun getUser(api: UserApi) = withContext(Dispatchers.IO) { repository.getUser(api) }

}