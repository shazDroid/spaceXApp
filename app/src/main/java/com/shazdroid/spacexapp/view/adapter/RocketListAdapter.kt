package com.shazdroid.spacexapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shazdroid.spacexapp.R
import com.shazdroid.spacexapp.databinding.ItemLayoutRocketBinding
import com.shazdroid.spacexapp.view.fragments.rocket_list.model.RocketListResponseItem

class RocketListAdapter(private val onItemClick : (String) -> Unit) : RecyclerView.Adapter<RocketListAdapter.ViewHolder>() {
    private var dataList : ArrayList<RocketListResponseItem>? = null
    lateinit var binding: ItemLayoutRocketBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_layout_rocket,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataList?.let { item ->
            // set data
            holder.bind(item[position])
            // on click listener
            holder.binding.root.setOnClickListener { onItemClick.invoke(item[position].id) }
        }
    }

    override fun getItemCount() = dataList?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<RocketListResponseItem>?){
        data?.let { this.dataList = it as ArrayList<RocketListResponseItem> }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: ItemLayoutRocketBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
        fun bind(item: RocketListResponseItem){
            binding.data = item
        }
    }
}