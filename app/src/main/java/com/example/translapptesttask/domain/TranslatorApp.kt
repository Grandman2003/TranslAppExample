package com.example.translapptesttask.domain

import android.app.Application
import com.example.translapptesttask.di.Injector
import com.example.translapptesttask.domain.translator.ProjectParameters

class TranslatorApp : Application() {
    val translatorComponent by lazy {
        Injector.appComponent.translateComponent().withTranslURL(
            ProjectParameters.TRANSL_URL.value
        ).appContext(applicationContext).build()
    }
}
