package com.letsmeatapp.letsmeatapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.responses.LoginResponse
import com.letsmeatapp.letsmeatapp.data.responses.User

open class UserObservableModel : ViewModel()
{
    val data = MutableLiveData<User>()

    fun setUserData(item: User) {
        data.value = item
    }
}