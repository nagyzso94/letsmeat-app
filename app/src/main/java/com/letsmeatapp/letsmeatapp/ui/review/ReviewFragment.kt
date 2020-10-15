package com.letsmeatapp.letsmeatapp.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.letsmeatapp.letsmeatapp.NestedReviewsNavigationArgs
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.network.ReviewApi
import com.letsmeatapp.letsmeatapp.data.repository.ReviewRepository
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.data.responses.User
import com.letsmeatapp.letsmeatapp.databinding.FragmentReviewBinding
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import com.letsmeatapp.letsmeatapp.ui.base.ObservableModel
import com.letsmeatapp.letsmeatapp.ui.restaurant.RestaurantListRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import kotlinx.android.synthetic.main.fragment_review.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ReviewFragment : BaseFragment<ReviewViewModel, FragmentReviewBinding, ReviewRepository>() {

    private var currentRestaurant: Restaurant? = null
    private val args: NestedReviewsNavigationArgs by navArgs()
    private val reviewListRecycleAdapter by lazy { ReviewListRecycleAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        currentRestaurant = args.nestedRestaurant
        val id = args.nestedRestaurant.id
        // current userId = userId
        // ellenőrzés: Toast.makeText(this.context, currentRestaurant?.id.toString()+"fragment"+ userId.toString(), Toast.LENGTH_SHORT).show()

        setupRecyclerView()
        viewModel.getReviewsbyRestaurantId(id)
        viewModel.restaurantReviews.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    //Toast.makeText(requireContext(), it.value.body().toString(), Toast.LENGTH_SHORT).show()
                    reviewListRecycleAdapter.setData(it.value.body()!!)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        })

    }

    fun setupRecyclerView() {
        review_recyclerview.adapter = reviewListRecycleAdapter
        review_recyclerview.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun getViewModel() = ReviewViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentReviewBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): ReviewRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ReviewApi::class.java, token)
        return ReviewRepository(api)
    }

}