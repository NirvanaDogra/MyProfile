package com.example.myprofile.view

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.commons.view.BaseScreenModel
import com.example.commons.view.BaseViewHolder
import com.example.myprofile.databinding.ItemShowProfileGenericPostBinding
import com.example.myprofile.viewmodel.GenericPostModel

class GenericPostViewHolder(private val binding: ItemShowProfileGenericPostBinding) :
    BaseViewHolder(binding.root) {
    override fun setViewData(viewDataModel: BaseScreenModel) {
        val dataModel = viewDataModel as GenericPostModel
        this.binding.postData = dataModel
        setColor(dataModel.imageUrl)
        binding.executePendingBindings()
    }

    private fun setColor(imageUrl: String?) {
        Glide.with(binding.root)
            .asBitmap()
            .centerCrop()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val palette = Palette.from(resource).generate().lightMutedSwatch
                    with(binding) {
                        textColor = palette?.titleTextColor ?: Color.WHITE
                        constraintShowProfileGeneralPost.setBackgroundColor(
                            palette?.rgb ?: Color.WHITE
                        )
                        imageviewShowProfileGeneralPostCenterImage.setImageBitmap(resource)
                        imageviewShowProfileGeneralPostCenterImage.scaleType =
                            ImageView.ScaleType.CENTER_CROP;
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
    }
}