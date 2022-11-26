package com.example.myprofile.model

import com.example.commons.view.BaseScreenModel
import com.example.myprofile.view.ViewSectionType
import com.google.gson.annotations.SerializedName

/*
 "whats-new-post": [
    {
      "heading": "Hello UIUC!",
      "subheading": "Starting a new journey",
      "description": "I am excited to announce ..."
    },
    {
      "heading": "Ram",
      "subheading": "ram@gmail.com",
      "description": 23
    },
    {
      "heading": "Ram",
      "subheading": "ram@gmail.com",
      "description": 23
    }
 */
data class WhatsNewPostModel(
    @SerializedName("heading")
    val heading: String?,
    @SerializedName("subheading")
    val subHeading: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("description")
    val postDescription: String?
) : BaseScreenModel {
    override val sectionType: ViewSectionType
        get() = ViewSectionType.GENERAL_POST
}