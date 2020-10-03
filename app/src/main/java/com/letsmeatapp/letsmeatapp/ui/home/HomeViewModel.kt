package com.letsmeatapp.letsmeatapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.repository.UserRepository
import com.letsmeatapp.letsmeatapp.data.responses.LoginResponse
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository
): ViewModel(){

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = repository.getUser()
    }
}