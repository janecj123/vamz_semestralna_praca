package com.example.semestralnapraca

import android.app.Application
import data.AppContainer
import data.AppDataContainer

class EFYBApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}