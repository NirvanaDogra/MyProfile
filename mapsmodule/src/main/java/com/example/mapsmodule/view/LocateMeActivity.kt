package com.example.mapsmodule.view

import android.graphics.Color
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mapsmodule.R
import com.example.mapsmodule.databinding.ActivityLocateMeAcitivtyBinding
import com.example.mapsmodule.model.DirectionResponseModel
import com.example.mapsmodule.viewmodel.LocateMeEvent
import com.example.mapsmodule.viewmodel.LocateMeState
import com.example.mapsmodule.viewmodel.LocateMeViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil

class LocateMeActivity : FragmentActivity(), OnMapReadyCallback {
    lateinit var dataBinding: ActivityLocateMeAcitivtyBinding
    private lateinit var fragment: SupportMapFragment
    private lateinit var viewModel: LocateMeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_locate_me_acitivty)
        initializeView()
        observeState()
    }

    private fun observeEvent(googleMap: GoogleMap) {
        viewModel.viewEvent.observe(this) {
            handleEvents(it, googleMap)
        }
    }

    private fun handleEvents(event: LocateMeEvent, googleMap: GoogleMap) {
        when (event) {
            is LocateMeEvent.PathRetrievedSuccessful -> handlePathRetrievedSuccess(
                event.directionResponseModel,
                googleMap
            )
        }
    }

    private fun handlePathRetrievedSuccess(response: DirectionResponseModel, googleMap: GoogleMap) {
        val shape = response.routes[0].overview_polyline.points
        val polyline = PolylineOptions()
            .addAll(PolyUtil.decode(shape))
            .width(8f)
            .color(Color.RED)


        val lat = response.routes[0].legs[0].start_location.lat
        val lng = response.routes[0].legs[0].start_location.lng
        val location = LatLng(lat, lng)
        googleMap.apply {
            addPolyline(polyline)
            moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16.0f))
        }
    }

    private fun observeState() {
        viewModel.viewState.observe(this) {
            handleState(it)
        }
    }

    private fun handleState(locateMeState: LocateMeState) {
        if (locateMeState.isError) {
            // error
        }
        if (locateMeState.isLoading) {
            // loding screen
        } else {
            // hide
        }
    }

    private fun initializeView() {
        fragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment_locate_me) as SupportMapFragment
        fragment.getMapAsync(this)
        viewModel = ViewModelProvider(this)[LocateMeViewModel::class.java]
    }

    override fun onMapReady(googleMap: GoogleMap) {
        observeEvent(googleMap)
    }
}
