package com.letsmeatapp.letsmeatapp.ui.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.data.responses.Review
import com.letsmeatapp.letsmeatapp.data.responses.ReviewResponseItem
import kotlinx.android.synthetic.main.restaurant_item_row.view.*
import kotlinx.android.synthetic.main.restaurant_item_row.view.row_whos_review
import kotlinx.android.synthetic.main.review_item_row.view.*

class ReviewListRecycleAdapter : RecyclerView.Adapter<ReviewListRecycleAdapter.MyViewHolder>(){

    private var reviewList = emptyList<ReviewResponseItem>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.itemView.row_whos_review.text = reviewList[position].userName
        holder.itemView.other_aspect_rating.text = reviewList[position].other_aspect

        // Load the rating values
        holder.itemView.savourinnes_rating.rating = reviewList[position].savouriness.toFloat()
        holder.itemView.prices_rating.rating = reviewList[position].prices.toFloat()
        holder.itemView.service_rating.rating = reviewList[position].service.toFloat()
        holder.itemView.cleanness_rating.rating = reviewList[position].cleanness.toFloat()

    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun setData(newList: List<ReviewResponseItem>){
        reviewList = newList
        notifyDataSetChanged()
    }
}