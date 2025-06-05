package com.example.ecomapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {

    init {
        _instance = this
    }

    companion object{
        private var _instance:MyApplication? = null

        fun getAppInstance():MyApplication {
            return _instance as MyApplication

        }
    }
    override fun onCreate() {
        super.onCreate()

    }
}