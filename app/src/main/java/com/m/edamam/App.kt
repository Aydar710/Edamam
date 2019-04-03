package com.m.edamam

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.m.edamam.di.component.DaggerPresenterComponent
import com.m.edamam.di.component.PresenterComponent
import com.m.edamam.di.module.AppModule

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null

        lateinit var presenterComponent: PresenterComponent
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        presenterComponent = DaggerPresenterComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
