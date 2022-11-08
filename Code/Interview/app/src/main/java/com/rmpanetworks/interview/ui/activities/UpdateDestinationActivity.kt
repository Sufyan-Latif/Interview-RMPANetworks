package com.rmpanetworks.interview.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.rmpanetworks.interview.R
import com.rmpanetworks.interview.databinding.ActivityUpdateDestinationBinding
import com.rmpanetworks.interview.utils.Utils

class UpdateDestinationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mGoogleMap: GoogleMap
    private var cameraDragged = false
    private lateinit var destinationLatLng: LatLng
    private var mapFrag: SupportMapFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapFrag = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFrag?.getMapAsync(this)

        binding.btnDone.setOnClickListener {
            val center: LatLng = mGoogleMap.cameraPosition.target
            if (center.latitude != 0.0 && center.longitude != 0.0) {
                Utils.destinationLatLng = LatLng(center.latitude, center.longitude)
                Utils.destinationChanged = true
            }
            finish()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        mGoogleMap = map
        /*mGoogleMap.setOnCameraIdleListener {
            val center: LatLng = mGoogleMap.cameraPosition.target
            if (center.latitude != 0.0 && center.longitude != 0.0) {
                if (cameraDragged) {
                    Utils.destinationLatLng = LatLng(center.latitude, center.longitude)
                }
            }
        }

        mGoogleMap.setOnCameraMoveStartedListener { i ->
            if (i == OnCameraMoveStartedListener.REASON_GESTURE
                || i == OnCameraMoveStartedListener.REASON_API_ANIMATION) cameraDragged =
                true else if (i == OnCameraMoveStartedListener.REASON_DEVELOPER_ANIMATION) cameraDragged =
                false
        }*/

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Utils.destinationLatLng, 14F))
    }
}