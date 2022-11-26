package com.example.myprofile.viewmodel

import com.example.commons.view.BaseScreenModel
import com.example.myprofile.view.ViewSectionType

data class GenericPostModel(
    val heading: String?,
    val subHeading: String?,
    val imageUrl: String?,
    val postDescription: String?
) : BaseScreenModel {
    override val sectionType: ViewSectionType
        get() = ViewSectionType.GENERAL_POST
}