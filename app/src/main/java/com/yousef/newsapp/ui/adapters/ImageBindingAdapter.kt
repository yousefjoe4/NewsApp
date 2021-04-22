package com.yousef.newsapp.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * This is used as an xml attribute to load images using Picasso (i.e: app:imageUrl)
 */
class ImageBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun loadImage(view: ImageView, imageUrl: String?) {
            if (imageUrl != null && imageUrl.isNotEmpty()) {
                Picasso.get().load(imageUrl).into(view)

            }
        }
    }
}