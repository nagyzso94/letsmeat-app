package com.letsmeatapp.letsmeatapp.ui.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.databinding.ActivityAuthBinding.inflate
import com.letsmeatapp.letsmeatapp.ui.base.BindingViewHolder
import com.letsmeatapp.letsmeatapp.ui.base.viewHolderFrom
import kotlinx.android.synthetic.main.restaurant_item_row.view.*

class RestaurantListRecyclerViewAdapater : RecyclerView.Adapter<RestaurantListRecyclerViewAdapater.MyViewHolder>(){

    private var restaurantList = emptyList<Restaurant>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.itemView.row_restaurant_name.text = restaurantList[position].name

        // TODO a képeket a típus alapján betölteni
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    fun setData(newList: List<Restaurant>){
        restaurantList = newList
        notifyDataSetChanged()
    }

}