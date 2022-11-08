package com.rmpanetworks.interview

import android.app.Application
import com.google.android.libraries.places.api.Places

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, resources.getString(R.string.google_maps_key))
        }
    }
}