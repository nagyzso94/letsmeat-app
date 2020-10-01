package com.letsmeatapp.letsmeatapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmeatapp.letsmeatapp.network.AuthApi
import com.letsmeatapp.letsmeatapp.network.Resource
import com.letsmeatapp.letsmeatapp.repository.AuthRepository
import com.letsmeatapp.letsmeatapp.responses.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(email,password)
    }

}