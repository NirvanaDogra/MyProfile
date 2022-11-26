package com.example.myprofile.viewmodel

import com.example.commons.view.BaseScreenModel

sealed class ShowProfileEvent {
    object OnNetworkError : ShowProfileEvent()
    object ShowErrorDialog : ShowProfileEvent()
    object FindMeClicked : ShowProfileEvent()
    data class OnSuccess(val data: MutableList<BaseScreenModel>) : ShowProfileEvent()

}