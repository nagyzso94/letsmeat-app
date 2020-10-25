package com.letsmeatapp.letsmeatapp.ui.restaurant

import android.os.Bundle
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
import com.letsmeatapp.letsmeatapp.databinding.FragmentRestaurantListBinding
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import com.letsmeatapp.letsmeatapp.ui.handleApiError
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class RestaurantListFragment :
    BaseFragment<RestaurantViewModel, FragmentRestaurantListBinding, RestaurantRepository>() {

    private val restaurantListRecyclerViewAdapter by lazy { RestaurantListRecyclerViewAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        viewModel.getRestaurants()
        viewModel.getReviewNumbers()
        viewModel.restaurants.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    //Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                    viewModel.reviewNumbers.observe(viewLifecycleOwner, Observer { response ->
                        when(response){
                            is Resource.Success -> {
                                restaurantListRecyclerViewAdapter.setData(it.value.body()!!, response.value.body()!!)
                            }
                        }
                    })
                }
                is Resource.Failure -> handleApiError(it) { viewModel.getRestaurants(); viewModel.getReviewNumbers()  }
            }

        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_restaurantListFragment_to_restaurantAddFragment)
        }

    }

    private fun setupRecyclerView() {
        restaurant_list_recyclreview.adapter = restaurantListRecyclerViewAdapter
        restaurant_list_recyclreview.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun getViewModel(): Class<RestaurantViewModel> = RestaurantViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRestaurantListBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): RestaurantRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(RestaurantApi::class.java, token)
        return RestaurantRepository(api)
    }
}