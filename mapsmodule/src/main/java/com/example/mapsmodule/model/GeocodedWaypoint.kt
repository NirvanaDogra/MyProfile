package com.example.mapsmodule.model

data class GeocodedWaypoint(
    val geocoder_status: String,
    val partial_match: Boolean,
    val place_id: String,
    val types: List<String>
)