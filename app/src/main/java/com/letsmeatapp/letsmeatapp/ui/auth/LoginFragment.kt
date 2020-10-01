package com.letsmeatapp.letsmeatapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.letsmeatapp.letsmeatapp.BaseFragment
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.databinding.FragmentLoginBinding
import com.letsmeatapp.letsmeatapp.network.AuthApi
import com.letsmeatapp.letsmeatapp.network.RemoteDataSource
import com.letsmeatapp.letsmeatapp.network.Resource
import com.letsmeatapp.letsmeatapp.repository.AuthRepository

class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        })


        binding.loginBtn.setOnClickListener {
            val email = binding.loginEmail.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()

            //@todo add validations
            viewModel.login(email,password)
        }


    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}