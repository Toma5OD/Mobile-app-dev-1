package org.wit.placemark.main

import android.app.Application
import org.wit.placemark.models.PlacemarkJSONStore
import org.wit.placemark.models.PlacemarkStore
import org.wit.placemark.models.*
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var placemarks: PlacemarkStore
    lateinit var users: UserStore


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        placemarks = PlacemarkJSONStore(applicationContext)
        users = UserJSONStore(applicationContext)
        i("Placemark started")
    }
}