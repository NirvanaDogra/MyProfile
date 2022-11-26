package com.example.myprofile.viewmodel

import com.example.commons.view.BaseScreenModel

data class ShowProfileState(
    val isLoading: Boolean,
    val isError: Boolean,
    val postList: List<BaseScreenModel> = listOf(
        MainHeadingModel("Hello")
    )
)