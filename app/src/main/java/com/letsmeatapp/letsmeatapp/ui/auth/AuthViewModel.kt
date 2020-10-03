package com.letsmeatapp.letsmeatapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.repository.AuthRepository
import com.letsmeatapp.letsmeatapp.data.responses.LoginResponse
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

    fun saveAuthToken(token:String){
        viewModelScope.launch {
            repository.saveAuthToken(token)
        }
    }

}