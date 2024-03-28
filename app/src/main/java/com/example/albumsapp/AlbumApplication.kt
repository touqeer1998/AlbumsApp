package com.example.albumsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AlbumApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.d("App Created")
    }

    override fun onTerminate() {
        super.onTerminate()
        Timber.d("App Terminated")
    }
}