package com.letsmeatapp.letsmeatapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.repository.UserRepository
import com.letsmeatapp.letsmeatapp.data.responses.LoginResponse
import com.letsmeatapp.letsmeatapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = repository.getUser()
    }
}