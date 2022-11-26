package com.example.myprofile.usecase

import com.example.myprofile.model.PostDataModel
import com.example.myprofile.model.ProfileDataService
import com.example.network.Network
import com.example.network.environment.GithubEnvironmentUrl.BASEURL
import io.reactivex.Observable

class ShowActivityUseCaseImpl : ShowActivityUseCase {
    override fun fetchWhatsNewPostContent(): Observable<PostDataModel> {
        val service = Network.getRetrofit(BASEURL)
            .create(ProfileDataService::class.java)
        return service.getWhatsNewPostData()
    }
}