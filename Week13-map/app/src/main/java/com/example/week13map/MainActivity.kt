package com.example.week13map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var myMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mapFragent: SupportMapFragment = getSupportFragmentManager().findFragmentById(R.id.map) as SupportMapFragment
        mapFragent.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        myMap = p0
        var Hanoi:LatLng = LatLng(21.02861, 105.85278)
        myMap.addMarker(MarkerOptions().position(Hanoi).title("Ha noi"))
        myMap.moveCamera(CameraUpdateFactory.newLatLng(Hanoi))
    }
}