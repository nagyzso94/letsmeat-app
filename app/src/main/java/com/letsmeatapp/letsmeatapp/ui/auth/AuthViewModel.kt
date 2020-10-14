package com.letsmeatapp.letsmeatapp.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.repository.AuthRepository
import com.letsmeatapp.letsmeatapp.data.responses.GenericResponse
import com.letsmeatapp.letsmeatapp.data.responses.LoginResponse
import com.letsmeatapp.letsmeatapp.data.responses.RegisterResponse
import com.letsmeatapp.letsmeatapp.data.responses.User
import com.letsmeatapp.letsmeatapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(email,password)
    }

    suspend fun saveAuthToken(token:String){
        repository.saveAuthToken(token)
    }

    suspend fun saveUserData(userId: Int){
        repository.saveUserData(userId)
    }

    private val _registerResponse : MutableLiveData<Resource<RegisterResponse>> = MutableLiveData()
    val registerResponse: LiveData<Resource<RegisterResponse>>
        get() = _registerResponse

    fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ) = viewModelScope.launch {
        _registerResponse.value = repository.register(name,email,password,passwordConfirmation)
        Log.d("resp",_registerResponse.value.toString())
    }
}