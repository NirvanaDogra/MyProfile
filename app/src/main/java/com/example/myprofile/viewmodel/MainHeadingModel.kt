package com.example.myprofile.viewmodel

import com.example.commons.view.BaseScreenModel
import com.example.myprofile.view.ViewSectionType

data class MainHeadingModel(
    val headingText: String
) : BaseScreenModel {
    override val sectionType: ViewSectionType
        get() = ViewSectionType.HOME_PAGE
}