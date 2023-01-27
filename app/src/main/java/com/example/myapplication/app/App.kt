package com.example.myapplication.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application(){

    companion object{
        var instanse:App? = null

        fun getInstanseApp() = instanse!!
    }

    override fun onCreate() {
        super.onCreate()
        instanse = this
    }
}