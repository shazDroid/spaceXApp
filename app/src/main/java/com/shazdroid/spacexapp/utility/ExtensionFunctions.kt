package com.shazdroid.spacexapp.utility

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shazdroid.spacexapp.R

fun ImageView.loadImage(url: String ){
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.icon_rocket)
        .into(this)
}