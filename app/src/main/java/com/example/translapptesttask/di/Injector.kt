package com.example.translapptesttask.di

import com.example.translapptesttask.di.components.AppComponent
import com.example.translapptesttask.di.components.DaggerAppComponent
import com.example.translapptesttask.domain.translator.ProjectParameters

object Injector {
    val appComponent: AppComponent = DaggerAppComponent.create()
}