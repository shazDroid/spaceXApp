package com.shazdroid.spacexapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shazdroid.spacexapp.R
import com.shazdroid.spacexapp.databinding.ItemLayoutRocketImagesBinding

class RocketImageListAdapter constructor(private val onImageSelect: (String) -> Unit) : RecyclerView.Adapter<RocketImageListAdapter.ViewHolder>() {
    var imageList: java.util.ArrayList<String>? = null
    lateinit var binding: ItemLayoutRocketImagesBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_layout_rocket_images, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList?.get(position) ?: "")
        holder.binding.root.setOnClickListener { onImageSelect.invoke(imageList?.get(position) ?: "") }
    }

    override fun getItemCount() = imageList?.size ?: 0

    fun setData(imageList: List<String?>?) {
        this.imageList = imageList as ArrayList<String>?
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: ItemLayoutRocketImagesBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
        fun bind(url: String){
            binding.url = url
        }
    }
}