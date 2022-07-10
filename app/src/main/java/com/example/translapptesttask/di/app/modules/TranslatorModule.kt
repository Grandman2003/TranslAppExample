package com.example.translapptesttask.di.app.modules

import com.example.core_app_api.TranslatorStarter
import com.example.translapptesttask.start.TranslatorStarterImpl
import dagger.Binds
import dagger.Module

@Module
internal interface TranslatorModule {
    @Binds
    fun getTranslatorStarter(translatorStarterImpl: TranslatorStarterImpl): TranslatorStarter
}
