package com.example.myprofile.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.commons.view.BaseScreenModel
import com.example.myprofile.model.PostDataModel
import com.example.myprofile.usecase.ShowActivityUseCaseImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class ShowProfileViewModel : ViewModel() {
    private val mutableViewState = MutableLiveData<ShowProfileState>()
    val viewState: LiveData<ShowProfileState>
        get() = mutableViewState

    private val mutableViewEvent = MutableLiveData<ShowProfileEvent>()
    val viewEvent: LiveData<ShowProfileEvent>
        get() = mutableViewEvent

    init {
        mutableViewState.value = ShowProfileState(isLoading = true, isError = false)
        makeInitialCalls()
    }

    fun showLocateMe() {
        mutableViewEvent.value = ShowProfileEvent.FindMeClicked
    }

    @SuppressLint("CheckResult")
    private fun makeInitialCalls() {
        ShowActivityUseCaseImpl().fetchWhatsNewPostContent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ postListData ->
                mutableViewState.value =
                    mutableViewState.value?.copy(isError = false, isLoading = false)

                val postList = getPostList(postListData)
                mutableViewState.value?.postList?.let { currentList ->
                    postList.addAll(0, currentList)
                }
                mutableViewEvent.value = ShowProfileEvent.OnSuccess(postList)

                Log.d("success", postListData.whatsNewPostList.toString())
            }, {
                if (it is UnknownHostException) {
                    mutableViewEvent.value = ShowProfileEvent.OnNetworkError
                }
                mutableViewState.value =
                    mutableViewState.value?.copy(isError = true, isLoading = false)
                Log.d("Error", it.toString())
            })
    }

    fun downNavigationClicked() {
        mutableViewEvent.value = ShowProfileEvent.DownNavigationButtonClicked
    }

    private fun getPostList(postDataModel: PostDataModel): MutableList<BaseScreenModel> {
        val postList = mutableListOf<BaseScreenModel>()
        for (postData in postDataModel.whatsNewPostList) {
            with(postData) {
                postList.add(
                    GenericPostModel(
                        heading = heading,
                        subHeading = subHeading,
                        imageUrl = imageUrl,
                        postDescription = postDescription
                    )
                )
            }
        }
        return postList
    }
}