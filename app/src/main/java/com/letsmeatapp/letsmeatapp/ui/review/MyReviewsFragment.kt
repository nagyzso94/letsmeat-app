package com.letsmeatapp.letsmeatapp.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.data.network.ReviewApi
import com.letsmeatapp.letsmeatapp.data.repository.ReviewRepository
import com.letsmeatapp.letsmeatapp.databinding.FragmentMyReviewsBinding
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import com.letsmeatapp.letsmeatapp.ui.handleApiError
import com.letsmeatapp.letsmeatapp.ui.visible
import kotlinx.android.synthetic.main.fragment_review.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class MyReviewsFragment :
    BaseFragment<ReviewViewModel, FragmentMyReviewsBinding, ReviewRepository>() {

    private val reviewListRecycleAdapter by lazy { MyReviewsListRecycleAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        viewModel.getReviewsbyUserId(userId!!)
        viewModel.restaurantReviews.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    if (it.value.body()?.isEmpty()!!) {
                        binding.textView2.text = "Még nem írtál véleményt :("
                        binding.emptyReviews.visible(true)
                        binding.textView2.visible(true)
                    } else {
                        reviewListRecycleAdapter.setData(it.value.body()!!)
                    }
                }
                is Resource.Failure -> handleApiError(it) { viewModel.getReviewsbyUserId(userId!!) }
            }
        })
    }

    private fun setupRecyclerView() {
        review_recyclerview.adapter = reviewListRecycleAdapter
        review_recyclerview.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun getViewModel() = ReviewViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMyReviewsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ReviewRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ReviewApi::class.java, token)
        return ReviewRepository(api)
    }
}