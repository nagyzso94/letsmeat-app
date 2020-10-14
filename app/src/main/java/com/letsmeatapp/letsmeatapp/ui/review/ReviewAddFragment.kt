package com.letsmeatapp.letsmeatapp.ui.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.network.ReviewApi
import com.letsmeatapp.letsmeatapp.data.repository.ReviewRepository
import com.letsmeatapp.letsmeatapp.databinding.FragmentReviewAddBinding
import com.letsmeatapp.letsmeatapp.databinding.FragmentReviewBinding
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ReviewAddFragment : BaseFragment<ReviewViewModel, FragmentReviewAddBinding, ReviewRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // todo ide jönnek majd az design elemekkel való összekötések, response megfigyelések
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