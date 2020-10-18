package com.letsmeatapp.letsmeatapp.ui.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.data.responses.ReviewNumbers
import kotlinx.android.synthetic.main.restaurant_item_row.view.*

class RestaurantListRecyclerViewAdapter :
    RecyclerView.Adapter<RestaurantListRecyclerViewAdapter.MyViewHolder>() {

    private var restaurantList = emptyList<Restaurant>()
    private var reviewNumbers = emptyList<ReviewNumbers>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.itemView.row_whose_review.text = restaurantList[position].name
        when (restaurantList[position].type) {
            0 -> holder.itemView.row_restaurant_type_pic.setImageResource(R.drawable.hungarian_food)
            1 -> holder.itemView.row_restaurant_type_pic.setImageResource(R.drawable.italian_food)
            2 -> holder.itemView.row_restaurant_type_pic.setImageResource(R.drawable.asian_food)
            3 -> holder.itemView.row_restaurant_type_pic.setImageResource(R.drawable.american_food)
            else -> holder.itemView.row_restaurant_type_pic.setImageResource(R.drawable.other_food)
        }


        for(nums in reviewNumbers){
            if(nums.restaurant_id == restaurantList[position].id && nums.count >= 1){
                holder.itemView.row_number_of_reviews_tv.text = "Vélemények száma: ${nums.count}"
            }
        }


        holder.itemView.row_background.setOnClickListener {
            val action =
                RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetails(
                    restaurantList[position]
                )
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    fun setData(newList: List<Restaurant>, newReviewNumbers: List<ReviewNumbers>) {
        restaurantList = newList
        reviewNumbers = newReviewNumbers
        notifyDataSetChanged()
    }

}