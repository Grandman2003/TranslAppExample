package com.example.translapptesttask.domain

import android.app.Application
import com.example.translapptesttask.di.Injector
import com.example.translapptesttask.di.components.AppComponent
import com.example.translapptesttask.di.components.DaggerAppComponent
import com.example.translapptesttask.domain.translator.ProjectParameters
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class TranslatorApp: Application(){
    val translatorComponent by lazy {
        Injector.appComponent.translateComponent().withTranslURL(
        ProjectParameters.TRANSL_URL.value).
        appContext(applicationContext).build()}
}