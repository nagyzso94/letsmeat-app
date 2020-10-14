package com.letsmeatapp.letsmeatapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.letsmeatapp.letsmeatapp.data.UserPreferences
import com.letsmeatapp.letsmeatapp.data.network.RemoteDataSource
import com.letsmeatapp.letsmeatapp.data.network.UserApi
import com.letsmeatapp.letsmeatapp.data.repository.BaseRepository
import com.letsmeatapp.letsmeatapp.ui.auth.AuthActivity
import com.letsmeatapp.letsmeatapp.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM: BaseViewModel, B: ViewBinding, R: BaseRepository> :  Fragment() {

    protected lateinit var binding : B
    protected val remoteDataSource = RemoteDataSource()
    protected lateinit var viewModel : VM
    protected lateinit var userPreferences: UserPreferences
    protected var userId: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        lifecycleScope.launch {
            userPreferences.authToken.first()
            userId = userPreferences.userId.first()
        }

        return binding.root
    }

    fun logout() = lifecycleScope.launch{
        val authToken = userPreferences.authToken.first()
        val api = remoteDataSource.buildApi(UserApi::class.java, authToken)
        viewModel.logout(api)
        userPreferences.clear()
        requireActivity().startNewActivity(AuthActivity::class.java)
    }

  //  fun getUser() = lifecycleScope.launch{
  //      userId = userPreferences.userId.first()
  //  }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R
}