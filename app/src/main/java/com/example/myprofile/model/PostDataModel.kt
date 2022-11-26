package com.example.myprofile.model

import com.google.gson.annotations.SerializedName

data class PostDataModel(
    @SerializedName("whats-new-post")
    val whatsNewPostList: List<WhatsNewPostModel>
)