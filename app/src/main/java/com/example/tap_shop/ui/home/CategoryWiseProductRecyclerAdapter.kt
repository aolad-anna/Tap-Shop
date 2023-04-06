package com.example.tap_shop.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tap_shop.R
import com.example.tap_shop.model.response.GetAllCategories
import com.example.tap_shop.model.response.GetProduct

class   CategoryWiseProductRecyclerAdapter: ListAdapter<GetProduct, CategoryWiseProductRecyclerAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<GetProduct>() {
        override fun areItemsTheSame(oldItem: GetProduct, newItem: GetProduct): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GetProduct, newItem: GetProduct): Boolean {
            return oldItem == newItem
        }
    }

    var onItemClicked: (GetProduct) -> Unit = {}

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageSongs: ImageView = itemView.findViewById(R.id.imageSongs)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
//        val description: TextView = itemView.findViewById(R.id.description)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_all_product_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.imageSongs.load(item.images[0])
        holder.title.text = item.title
        holder.price.text = "Price: $${item.price.toString()}"
//        holder.description.text = "Description: ${item.description}"
        holder.itemView.setOnClickListener{
            onItemClicked(currentList[position])
        }
    }
}