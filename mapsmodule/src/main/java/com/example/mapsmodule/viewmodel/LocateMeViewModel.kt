package com.example.mapsmodule.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapsmodule.BuildConfig
import com.example.mapsmodule.usecase.LocateMeUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocateMeViewModel : ViewModel() {
    private val usecase: LocateMeUseCase = LocateMeUseCase()
    private val mutableViewState = MutableLiveData<LocateMeState>()
    val viewState: LiveData<LocateMeState>
        get() = mutableViewState

    private val mutableViewEvent = MutableLiveData<LocateMeEvent>()
    val viewEvent: LiveData<LocateMeEvent>
        get() = mutableViewEvent

    init {
        getDirectionData()
        mutableViewState.value = LocateMeState(isLoading = true, isError = false)
    }

    @SuppressLint("CheckResult")
    fun getDirectionData() {
        usecase.makeDirectionCall(
            origin = "40.102378562370504, -88.23122260894557",
            destination = "40.09192319438652, -88.24832820269427",
            key = BuildConfig.GOOGLE_MAPS_API_KEY
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutableViewState.value =
                    mutableViewState.value?.copy(isError = false, isLoading = false)
                mutableViewEvent.value = LocateMeEvent.PathRetrievedSuccessful(it)
            }, {
                mutableViewState.value =
                    mutableViewState.value?.copy(isError = true, isLoading = false)
            })
    }
}