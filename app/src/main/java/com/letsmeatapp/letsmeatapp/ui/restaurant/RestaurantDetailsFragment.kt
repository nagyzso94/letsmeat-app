package com.letsmeatapp.letsmeatapp.ui.restaurant

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.network.RestaurantApi
import com.letsmeatapp.letsmeatapp.data.repository.RestaurantRepository
import com.letsmeatapp.letsmeatapp.data.responses.AvgReview
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.databinding.FragmentRestaurantDetailsBinding
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import com.letsmeatapp.letsmeatapp.ui.handleApiError
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class RestaurantDetailsFragment :
    BaseFragment<RestaurantViewModel, FragmentRestaurantDetailsBinding, RestaurantRepository>() {

    private val args: RestaurantDetailsFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val restaurant: Restaurant = args.currentItem

        viewModel.getRestaurantDetailsById(restaurant.id)
        viewModel.restaurantDetails.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    updateAvgReviewUI(it.value.avg_review)
                }
                is Resource.Failure -> handleApiError(it) { viewModel.getRestaurantDetailsById(restaurant.id) }
            }
        })

        binding.restaurantDetailNameTv.text = restaurant.name
        binding.restaurantDetailRaddressTv.text = restaurant.address
        binding.restaurantDetailRphoneTv.text = restaurant.phone_number

        binding.restaurantDetailGotowebsiteBtn.setOnClickListener {
            var url = restaurant.web_page
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            })
        }

        binding.restaurantDetailGotoreviewsBtn.setOnClickListener {
            val action =
                RestaurantDetailsFragmentDirections.actionRestaurantDetailsToNavReviews2(restaurant)
            findNavController().navigate(action)
        }
    }

    private fun updateAvgReviewUI(avgReview: AvgReview) {
        with(binding) {
            binding.savourinessRating.rating = avgReview.savouriness.toFloat()
            binding.pricesRating.rating = avgReview.prices.toFloat()
            binding.serviceRating.rating = avgReview.service.toFloat()
            binding.cleannessRating.rating = avgReview.cleanness.toFloat()
        }
    }

    override fun getViewModel(): Class<RestaurantViewModel> = RestaurantViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRestaurantDetailsBinding.inflate(inflater, container, false)


    override fun getFragmentRepository(): RestaurantRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(RestaurantApi::class.java, token)
        return RestaurantRepository(api)
    }
}