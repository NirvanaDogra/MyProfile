package com.example.mapsmodule.model

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LocateMeService {
    @GET("maps/api/directions/json")
    fun getDirection(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") key: String
    ): Observable<DirectionResponseModel>
}