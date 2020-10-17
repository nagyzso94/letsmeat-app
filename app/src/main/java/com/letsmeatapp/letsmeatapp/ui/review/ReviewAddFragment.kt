package com.letsmeatapp.letsmeatapp.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.network.ReviewApi
import com.letsmeatapp.letsmeatapp.data.repository.ReviewRepository
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.databinding.FragmentReviewAddBinding
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import com.letsmeatapp.letsmeatapp.ui.enable
import com.letsmeatapp.letsmeatapp.ui.handleApiError
import kotlinx.android.synthetic.main.review_item_row.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ReviewAddFragment : BaseFragment<ReviewViewModel, FragmentReviewAddBinding, ReviewRepository>() {

    private var currentRestaurant: Restaurant? = null
    private val args: ReviewAddFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        currentRestaurant = args.currentRestaurant

        //Toast.makeText(this.context, currentRestaurant!!.id.toString(), Toast.LENGTH_SHORT).show()
        binding.reviewSaveBtn.enable(false)

        viewModel.reviewCreateResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        Toast.makeText(
                            requireContext(),
                            "Sikeres vélemény hozzáadás!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val action =
                            ReviewAddFragmentDirections.actionReviewAddFragment2ToReviewFragment(
                                args.currentRestaurant
                            )
                        findNavController().navigate(action)
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                    handleApiError(it) { createReview() }
                }
            }
        })

        var savourinessSet: Boolean = false
        var pricesSet: Boolean = false
        var serviceSet: Boolean = false

        binding.addSavourinessRating.setOnRatingBarChangeListener { _, _, b -> savourinessSet = b }
        binding.addPricesRating.setOnRatingBarChangeListener { _, _, b -> pricesSet = b }
        binding.addServiceRating.setOnRatingBarChangeListener { _, _, b -> serviceSet = b }
        binding.addCleannessRating.setOnRatingBarChangeListener { _, _, b ->
            val userId = userId!!
            val restaurantId = args.currentRestaurant.id
            //Toast.makeText(requireContext(), "userId: $userId, restaurantId: $restaurantId, cleanness: ${add_cleanness_rating.rating}", Toast.LENGTH_SHORT).show()
            binding.reviewSaveBtn.enable(userId != 0 && restaurantId != 0 && savourinessSet && pricesSet && serviceSet && b)
        }

        binding.reviewSaveBtn.setOnClickListener {
            createReview()
        }
    }

    private fun createReview() {
        val userId = userId!!
        val restaurantId = args.currentRestaurant.id
        val savouriness = binding.addSavourinessRating.rating.toDouble()
        val prices = binding.addPricesRating.rating.toDouble()
        val service = binding.addServiceRating.rating.toDouble()
        val cleanness = binding.addCleannessRating.rating.toDouble()
        val otherAspect = binding.reviewAddOtherText.text.toString()
        viewModel.createReview(userId,restaurantId,savouriness,prices,service,cleanness, otherAspect)
    }

    override fun getViewModel() = ReviewViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentReviewAddBinding.inflate(inflater,container, false)

    override fun getFragmentRepository(): ReviewRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ReviewApi::class.java, token)
        return ReviewRepository(api)
    }
}