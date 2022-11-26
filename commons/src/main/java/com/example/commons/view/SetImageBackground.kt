package com.example.commons.view

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:image")
fun setImageForImageView(img: View, imageId: Drawable) {
    Glide.with(img.context)
        .load(imageId)
        .centerCrop()
        .into(img as ImageView)
}

@BindingAdapter("app:avatar_image")
fun setAvatarImage(img: View, imageId: Drawable) {
    Glide.with(img.context)
        .load(imageId)
        .circleCrop()
        .into(img as ImageView);
}

@BindingAdapter("app:image")
fun setImageForImageView(img: ImageView, imageUrl: String?) {
    Glide.with(img.context)
        .load(imageUrl)
        .centerCrop()
        .into(img)
}