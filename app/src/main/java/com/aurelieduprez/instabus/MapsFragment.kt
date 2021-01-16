package com.aurelieduprez.instabus

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurelieduprez.instabus.MainActivity.Companion.station
import com.aurelieduprez.instabus.data.Station

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
/**
 Fragment qui définit la mise en place de la map et les marqueurs a chaque station
 * */
class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
    var barcelona = LatLng(41.398, 2.19)
        station.forEach{ station: Station ->
            var stationPos = LatLng(station.lat, station.lon);
            var zoomLevel = 14.0f;
            googleMap.addMarker(MarkerOptions().position(stationPos).title(station.street_name).snippet(station.buses))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelona, zoomLevel))
        }
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */


    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}