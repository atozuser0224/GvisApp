package com.example.gvisapp

import android.app.Application
import com.example.gvisapp.data.HealthConnectManager

class BaseApplication : Application() {
    val healthConnectManager by lazy {
        HealthConnectManager(this)
    }
}