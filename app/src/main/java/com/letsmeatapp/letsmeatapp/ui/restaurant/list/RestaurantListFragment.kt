package com.letsmeatapp.letsmeatapp.ui.restaurant.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.network.RestaurantApi
import com.letsmeatapp.letsmeatapp.data.repository.RestaurantRepository
import kotlinx.android.synthetic.main.restaurant_list_fragment.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RestaurantListFragment : Fragment() {

    companion object {
        fun newInstance() = RestaurantListFragment()
    }

    private lateinit var viewModel: RestaurantListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.restaurant_list_fragment, container, false)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_restaurantListFragment_to_restaurantAddFragment)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}