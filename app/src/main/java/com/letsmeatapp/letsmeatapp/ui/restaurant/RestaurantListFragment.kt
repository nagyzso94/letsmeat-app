package com.letsmeatapp.letsmeatapp.ui.restaurant

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.network.RestaurantApi
import com.letsmeatapp.letsmeatapp.data.repository.RestaurantRepository
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.data.responses.RestaurantResponse
import com.letsmeatapp.letsmeatapp.databinding.RestaurantsFragmentBinding
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.restaurants_fragment.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody

class RestaurantListFragment : BaseFragment<RestaurantViewModel, RestaurantsFragmentBinding, RestaurantRepository>() {

    private val restaurantListRecyclerViewAdapter by lazy { RestaurantListRecyclerViewAdapater() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        viewModel.getRestaurants()
        var value: Resource<RestaurantResponse>? = viewModel.restaurants.value
        Log.d("log",value.toString())
        Log.d("log", viewModel.getRestaurants().toString())
        // TODO: itt van vmi bibi, mert null jön vissza és a failure ágra fut ez a cucli
        // + logban látom, hogy lejönnek az éttermek
        viewModel.restaurants.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    Log.d("log", it.toString())
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                    restaurantListRecyclerViewAdapter.setData(it.value.restaurants)
                }
                is Resource.Failure -> {
                    Log.d("log", it.toString())
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_restaurantListFragment_to_restaurantAddFragment)
        }

    }

    fun setupRecyclerView(){
        Log.d("recyclerview","meghívódott a setupRecyclerview")
        restaurant_list_recyclreview.adapter = restaurantListRecyclerViewAdapter
        restaurant_list_recyclreview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun getViewModel(): Class<RestaurantViewModel> = RestaurantViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = RestaurantsFragmentBinding.inflate(inflater, container,false)

    override fun getFragmentRepository(): RestaurantRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(RestaurantApi::class.java,token)
        return RestaurantRepository(api)
    }
}