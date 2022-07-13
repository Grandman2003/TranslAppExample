package com.example.translapptesttask.di.app.providers

import com.example.core_app_api.TranslatorInteractor
import com.example.translapptesttask.domain.translator.TranslatorInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface TranslatorInteractorsProvider {
    @Binds
    fun getTranslationInteractor(translInterImpl: TranslatorInteractorImpl): TranslatorInteractor
}
