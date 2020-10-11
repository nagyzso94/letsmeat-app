package com.letsmeatapp.letsmeatapp.ui.restaurant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.network.RemoteDataSource
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.network.RestaurantApi
import com.letsmeatapp.letsmeatapp.data.repository.AuthRepository
import com.letsmeatapp.letsmeatapp.data.repository.RestaurantRepository
import com.letsmeatapp.letsmeatapp.databinding.FragmentLoginBinding
import com.letsmeatapp.letsmeatapp.databinding.RestaurantAddFragmentBinding
import com.letsmeatapp.letsmeatapp.ui.auth.AuthViewModel
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import com.letsmeatapp.letsmeatapp.ui.enable
import com.letsmeatapp.letsmeatapp.ui.home.HomeActivity
import com.letsmeatapp.letsmeatapp.ui.startNewActivity
import kotlinx.android.synthetic.main.restaurant_add_fragment.*
import kotlinx.coroutines.launch

class RestaurantAddFragment : BaseFragment<RestaurantViewModel, RestaurantAddFragmentBinding, RestaurantRepository>() {


  /*  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_add_fragment, container, false)
    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.addRestaurantSave.enable(false)

        viewModel.restaurantCreateResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    lifecycleScope.launch {
                        findNavController().navigate(R.id.action_restaurantAddFragment_to_restaurantListFragment)
                        Toast.makeText(requireContext(),"Sikeres étterem hozzáadás!",Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Failure -> {
                    Log.d("log",it.toString())
                    //Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        })

        // TODO : Checking if every field is filled out, then enable save btn

        // TODO : Saving the restaurant

    }

    override fun getViewModel(): Class<RestaurantViewModel> = RestaurantViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = RestaurantAddFragmentBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = RestaurantRepository(remoteDataSource.buildApi(RestaurantApi::class.java))

}