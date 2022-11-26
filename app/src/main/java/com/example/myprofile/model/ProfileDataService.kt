package com.example.myprofile.model

import com.example.network.environment.GithubEnvironmentUrl.WHATS_NEW_POST_URL
import io.reactivex.Observable
import retrofit2.http.GET

interface ProfileDataService {
    @GET(WHATS_NEW_POST_URL)
    fun getWhatsNewPostData(): Observable<PostDataModel>
}