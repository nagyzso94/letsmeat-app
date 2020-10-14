package com.letsmeatapp.letsmeatapp.ui.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.data.responses.Review

class ReviewListRecycleAdapter : RecyclerView.Adapter<ReviewListRecycleAdapter.MyViewHolder>(){

    private var reviewList = emptyList<Review>()

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
        // TODO feltölteni adatokkal az elemeket
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun setData(newList: List<Review>){
        reviewList = newList
        notifyDataSetChanged()
    }
}