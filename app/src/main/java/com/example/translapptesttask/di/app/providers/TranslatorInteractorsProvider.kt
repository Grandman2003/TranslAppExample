package com.example.translapptesttask.di.app.providers

import com.example.core_app_api.TranslatorInteractor
import com.example.translapptesttask.data.net.interfaces.TranslateService
import com.example.translapptesttask.data.repositories.DictionaryRepository
import com.example.translapptesttask.domain.translator.TranslatorInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class TranslatorInteractorsProvider {

    @Provides
    @Inject
    fun getTranslatorInteractor(
        translService: TranslateService,
        dictionaryRepo: DictionaryRepository
    ): TranslatorInteractor =
        TranslatorInteractorImpl(translService, dictionaryRepo)
}
