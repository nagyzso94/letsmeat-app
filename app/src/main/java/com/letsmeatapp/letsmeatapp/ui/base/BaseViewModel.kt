package com.letsmeatapp.letsmeatapp.ui.base

import androidx.lifecycle.ViewModel
import com.letsmeatapp.letsmeatapp.data.network.UserApi
import com.letsmeatapp.letsmeatapp.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {
    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }
}