package com.example.myprofile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.commons.view.BaseSectionType
import com.example.commons.view.BaseViewHolder
import com.example.myprofile.databinding.ItemShowProfileGenericPostBinding
import com.example.myprofile.databinding.ItemShowProfileHomePostBinding
import com.example.myprofile.viewmodel.ShowProfileViewModel

enum class ViewSectionType : BaseSectionType {
    HOME_PAGE {
        override fun getViewHolder(
            parent: ViewGroup,
            viewModel: ShowProfileViewModel
        ): BaseViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemShowProfileHomePostBinding =
                ItemShowProfileHomePostBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            return HeadingViewHolder(binding, viewModel)
        }
    },
    GENERAL_POST {
        override fun getViewHolder(
            parent: ViewGroup,
            viewModel: ShowProfileViewModel
        ): BaseViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemShowProfileGenericPostBinding =
                ItemShowProfileGenericPostBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            return GenericPostViewHolder(binding)
        }

    };

    abstract fun getViewHolder(parent: ViewGroup, viewModel: ShowProfileViewModel): BaseViewHolder
}