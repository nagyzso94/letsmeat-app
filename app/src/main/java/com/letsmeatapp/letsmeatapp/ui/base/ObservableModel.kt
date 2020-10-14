package com.letsmeatapp.letsmeatapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.letsmeatapp.letsmeatapp.data.repository.BaseRepository
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.data.responses.User


open class ObservableModel(repository: BaseRepository) : BaseViewModel(repository)
{
  /*  val data = MutableLiveData<Any>()

    fun setUserData(item: User) {
        data.value = item
    }

    fun setRestaurantData(item: Restaurant){
        data.value = item
    }*/


    /**
     * Live Data Instance
     */
    private val mData = MutableLiveData<Any>()

    open fun setUserData(data: User) {
        mData.value = data
    }

    open fun getUserData(): MutableLiveData<Any> {
        return mData
    }
}