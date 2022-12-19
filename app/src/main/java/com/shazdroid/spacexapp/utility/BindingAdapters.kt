package com.shazdroid.spacexapp.utility

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.shazdroid.spacexapp.R
import com.shazdroid.spacexapp.view.adapter.RocketImageListAdapter
import com.shazdroid.spacexapp.view.adapter.RocketListAdapter


@BindingAdapter("onItemClickListener")
fun setListData(recyclerView: RecyclerView,onItemClick: OnItemClick){
    recyclerView.adapter?.let { adapter ->
    } ?: run {
        val adapter = RocketListAdapter {
            onItemClick.onItemClick(it)
        }
        recyclerView.adapter = adapter
    }
}

@BindingAdapter("onImageSelect")
fun onImageSelectBindingAdapter(recyclerView: RecyclerView,onImageSelect: OnImageSelect){
    recyclerView.adapter?.let { adapter ->
    } ?: run {
        val adapter = RocketImageListAdapter { url ->
            onImageSelect.onImageSelect(url)
        }
        recyclerView.adapter = adapter
    }
}

@BindingAdapter("loadImage")
fun loadImage(imageView: ShapeableImageView, url: String){
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.icon_rocket)
        .into(imageView)
}
