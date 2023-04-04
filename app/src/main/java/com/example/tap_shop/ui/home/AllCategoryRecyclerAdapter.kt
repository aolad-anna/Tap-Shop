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

class   AllCategoryRecyclerAdapter: ListAdapter<GetAllCategories, AllCategoryRecyclerAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<GetAllCategories>() {
        override fun areItemsTheSame(oldItem: GetAllCategories, newItem: GetAllCategories): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GetAllCategories, newItem: GetAllCategories): Boolean {
            return oldItem == newItem
        }
    }

//    override fun getItemCount(): Int {
//        return 5
//    }

    var onItemClicked: (GetAllCategories) -> Unit = {}

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageSongs: ImageView = itemView.findViewById(R.id.imageSongs)
        val title: TextView = itemView.findViewById(R.id.title)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_all_category_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.imageSongs.load(item.image)
        holder.title.text = item.name
        holder.itemView.setOnClickListener{
            onItemClicked(currentList[position])
        }
    }
}