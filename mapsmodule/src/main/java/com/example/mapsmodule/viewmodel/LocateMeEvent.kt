package com.example.mapsmodule.viewmodel

import com.example.mapsmodule.model.DirectionResponseModel

sealed class LocateMeEvent {
    data class PathRetrievedSuccessful(val directionResponseModel: DirectionResponseModel) :
        LocateMeEvent()
}