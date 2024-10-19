package com.example.postsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IcaApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}