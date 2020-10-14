package com.letsmeatapp.letsmeatapp.ui.restaurant

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.network.RestaurantApi
import com.letsmeatapp.letsmeatapp.data.repository.RestaurantRepository
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.databinding.FragmentRestaurantDetailsBinding
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import com.letsmeatapp.letsmeatapp.ui.review.ReviewActivity
import com.letsmeatapp.letsmeatapp.ui.startNewActivity
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class RestaurantDetailsFragment : BaseFragment<RestaurantViewModel, FragmentRestaurantDetailsBinding, RestaurantRepository>() {

    private val args: RestaurantDetailsFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val restaurant: Restaurant = args.currentItem

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
            val action = RestaurantDetailsFragmentDirections.actionRestaurantDetailsToNavReviews2(restaurant)
            findNavController().navigate(action)
        }
    }

    override fun getViewModel(): Class<RestaurantViewModel> = RestaurantViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRestaurantDetailsBinding.inflate(inflater,container,false)



    override fun getFragmentRepository(): RestaurantRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(RestaurantApi::class.java, token)
        return RestaurantRepository(api)
    }
}