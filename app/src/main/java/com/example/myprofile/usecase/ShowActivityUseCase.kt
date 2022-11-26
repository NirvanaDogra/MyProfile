package com.example.myprofile.usecase

import com.example.myprofile.model.PostDataModel
import io.reactivex.Observable

interface ShowActivityUseCase {
    fun fetchWhatsNewPostContent(): Observable<PostDataModel>
}