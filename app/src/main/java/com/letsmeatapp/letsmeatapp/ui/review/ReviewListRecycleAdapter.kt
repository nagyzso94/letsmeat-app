package com.letsmeatapp.letsmeatapp.ui.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.responses.Review
import kotlinx.android.synthetic.main.review_item_row.view.*

class ReviewListRecycleAdapter : RecyclerView.Adapter<ReviewListRecycleAdapter.MyViewHolder>(){

    private var reviewList = emptyList<Review>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.review_item_row,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.itemView.row_whose_review.text = reviewList[position].userName
        holder.itemView.add_savouriness_rating.rating = reviewList[position].savouriness.toFloat()
        holder.itemView.add_prices_rating.rating = reviewList[position].prices.toFloat()
        holder.itemView.add_service_rating.rating = reviewList[position].service.toFloat()
        holder.itemView.add_cleanness_rating.rating = reviewList[position].cleanness.toFloat()
        holder.itemView.other_aspect_rating.text = reviewList[position].other_aspect
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun setData(newList: List<Review>){
        reviewList = newList
        notifyDataSetChanged()
    }
}