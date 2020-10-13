package com.letsmeatapp.letsmeatapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.letsmeatapp.letsmeatapp.data.network.AuthApi
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.repository.AuthRepository
import com.letsmeatapp.letsmeatapp.databinding.FragmentRegisterBinding
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import com.letsmeatapp.letsmeatapp.ui.enable
import com.letsmeatapp.letsmeatapp.ui.handleApiError
import com.letsmeatapp.letsmeatapp.ui.startNewActivity
import kotlinx.coroutines.launch


class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.registerBtn.enable(false)

        viewModel.registerResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    lifecycleScope.launch {
                        requireActivity().startNewActivity(AuthActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it){
                    register()
                }
            }
        })

        binding.registerPasswordConfirmation.addTextChangedListener {
            val name = binding.registerName.text.toString()
            val email = binding.registerEmail.text.toString().trim()
            val password = binding.registerPassword.text.toString().trim()
            binding.registerBtn.enable(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.registerBtn.setOnClickListener {
            register()
        }

    }

    private fun register(){
        val name = binding.registerName.text.toString()
        val email = binding.registerEmail.text.toString().trim()
        val password = binding.registerPassword.text.toString().trim()
        val passwordConfirmation = binding.registerPasswordConfirmation.text.toString().trim()
        viewModel.register(name,email,password,passwordConfirmation)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java),userPreferences)


}