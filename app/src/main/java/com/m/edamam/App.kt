package com.m.edamam

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

open class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
         var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
