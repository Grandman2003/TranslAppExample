package com.example.translapptesttask.di.app.modules

import com.example.translapptesttask.data.net.interfaces.TranslateService
import com.example.translapptesttask.domain.translator.TranslatorInteractor
import com.example.translapptesttask.domain.translator.TranslatorInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class TranslatorInjectorsProvider {
    @Provides
    @Inject
    fun getTRanslatorInjector(translService: TranslateService,): TranslatorInteractor
        = TranslatorInteractorImpl(translService)
}