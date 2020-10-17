package com.letsmeatapp.letsmeatapp.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.network.RestaurantApi
import com.letsmeatapp.letsmeatapp.data.repository.RestaurantRepository
import com.letsmeatapp.letsmeatapp.databinding.RestaurantAddFragmentBinding
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import com.letsmeatapp.letsmeatapp.ui.enable
import com.letsmeatapp.letsmeatapp.ui.handleApiError
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class RestaurantAddFragment :
    BaseFragment<RestaurantViewModel, RestaurantAddFragmentBinding, RestaurantRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.addRestaurantSave.enable(false)

        viewModel.restaurantCreateResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        findNavController().navigate(R.id.action_restaurantAddFragment_to_restaurantListFragment)
                        Toast.makeText(
                            requireContext(),
                            "Sikeres étterem hozzáadás!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is Resource.Failure -> {
                    handleApiError(it) { createRestaurant() }
                }
            }
        })

        binding.restaurantAddTitle.addTextChangedListener {
            checkNameValue(it.toString())
        }

        binding.restaurantAddWebUri.addTextChangedListener {
            val name: String = binding.restaurantAddTitle.text.toString()
            val address: String = binding.restaurantAddAddress.text.toString()
            val phoneNumber: String = binding.restaurantAddPhoneNumber.text.toString()
            val webUri: String = binding.restaurantAddWebUri.text.toString()
            val type: Int = binding.typeSpinner.selectedItemPosition
            binding.addRestaurantSave.enable(name.isNotEmpty() && address.isNotEmpty() && phoneNumber.isNotEmpty() && webUri.isNotEmpty() && type != -1)
        }

        binding.addRestaurantSave.setOnClickListener {
            createRestaurant()
        }

    }

    private fun createRestaurant() {
        val name: String = binding.restaurantAddTitle.text.toString()
        val address: String = binding.restaurantAddAddress.text.toString()
        val phoneNumber: String = binding.restaurantAddPhoneNumber.text.toString()
        val webUri: String = binding.restaurantAddWebUri.text.toString()
        val type: Int = binding.typeSpinner.selectedItemPosition
        viewModel.createRestaurant(name, address, phoneNumber, webUri, type)
    }

    private fun checkNameValue(name: String): Boolean {
        return if (name.isEmpty()) {
            binding.restaurantAddTitle.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                AppCompatResources.getDrawable(requireContext(), R.drawable.ic_error),
                null
            )
            true
        } else {
            binding.restaurantAddTitle.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                AppCompatResources.getDrawable(requireContext(), R.drawable.ic_check),
                null
            )
            false
        }
    }

    override fun getViewModel(): Class<RestaurantViewModel> = RestaurantViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = RestaurantAddFragmentBinding.inflate(inflater, container, false)


    override fun getFragmentRepository(): RestaurantRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(RestaurantApi::class.java, token)
        return RestaurantRepository(api)
    }

}