package com.letsmeatapp.letsmeatapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.letsmeatapp.letsmeatapp.data.repository.AuthRepository
import com.letsmeatapp.letsmeatapp.data.repository.BaseRepository
import com.letsmeatapp.letsmeatapp.data.repository.RestaurantRepository
import com.letsmeatapp.letsmeatapp.data.repository.UserRepository
import com.letsmeatapp.letsmeatapp.ui.auth.AuthViewModel
import com.letsmeatapp.letsmeatapp.ui.profile.ProfileViewModel
import com.letsmeatapp.letsmeatapp.ui.restaurant.RestaurantViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository : BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(RestaurantViewModel::class.java) -> RestaurantViewModel(repository as RestaurantRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}