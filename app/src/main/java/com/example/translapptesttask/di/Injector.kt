package com.example.translapptesttask.di

import com.example.translapptesttask.di.components.AppComponent
import com.example.translapptesttask.di.components.DaggerAppComponent

object Injector {
    val appComponent: AppComponent = DaggerAppComponent.create()
}
