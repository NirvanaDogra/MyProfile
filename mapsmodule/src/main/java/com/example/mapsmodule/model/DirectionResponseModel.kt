package com.example.mapsmodule.model

data class DirectionResponseModel(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String
)