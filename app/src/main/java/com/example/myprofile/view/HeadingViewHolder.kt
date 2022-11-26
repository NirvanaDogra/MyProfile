package com.example.myprofile.view

import com.example.commons.view.BaseScreenModel
import com.example.commons.view.BaseViewHolder
import com.example.myprofile.databinding.ItemShowProfileHomePostBinding
import com.example.myprofile.viewmodel.MainHeadingModel
import com.example.myprofile.viewmodel.ShowProfileViewModel

class HeadingViewHolder(
    private val binding: ItemShowProfileHomePostBinding,
    val viewModel: ShowProfileViewModel
) :
    BaseViewHolder(binding.root) {
    override fun setViewData(viewDataModel: BaseScreenModel) {
        binding.text = (viewDataModel as MainHeadingModel).headingText
        binding.viewmodel = viewModel
        binding.executePendingBindings()
    }
}