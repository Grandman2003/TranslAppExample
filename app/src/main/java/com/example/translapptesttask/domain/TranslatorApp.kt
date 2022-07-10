package com.example.translapptesttask.domain

import android.app.Application
import android.content.Context
import com.example.translapptesttask.di.components.AppComponent
import com.example.translapptesttask.di.components.DaggerAppComponent

class TranslatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        AppComponent.init(
            DaggerAppComponent.builder().build()
        )
        AppComponent.get().inject(this)
    }

    companion object {
        @Volatile
        lateinit var appContext: Context
            private set
    }
}
