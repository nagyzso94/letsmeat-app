package com.letsmeatapp.letsmeatapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.letsmeatapp.letsmeatapp.data.repository.AuthRepository
import com.letsmeatapp.letsmeatapp.data.repository.BaseRepository
import com.letsmeatapp.letsmeatapp.data.repository.UserRepository
import com.letsmeatapp.letsmeatapp.ui.auth.AuthViewModel
import com.letsmeatapp.letsmeatapp.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository : BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}