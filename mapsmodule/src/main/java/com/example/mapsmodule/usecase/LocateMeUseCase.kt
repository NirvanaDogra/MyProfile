package com.example.mapsmodule.usecase

import com.example.mapsmodule.model.DirectionResponseModel
import com.example.mapsmodule.model.LocateMeService
import com.example.network.Network
import io.reactivex.Observable

class LocateMeUseCase() {
    fun makeDirectionCall(
        origin: String,
        destination: String,
        key: String
    ): Observable<DirectionResponseModel> {
        val client =
            Network.getRetrofit("https://maps.googleapis.com")
                .create(LocateMeService::class.java)
        return client.getDirection(origin, destination, key)
    }
}