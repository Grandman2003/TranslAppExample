package com.example.translapptesttask.di.components

import com.example.core_app_api.TranslationApi
import com.example.translapptesttask.di.PerFeature
import com.example.translapptesttask.di.app.modules.AppModule
import com.example.translapptesttask.di.app.providers.DictionaryProvider
import com.example.translapptesttask.di.app.components.TranslationDependencies
import com.example.translapptesttask.di.app.providers.TranslatorInteractorsProvider
import com.example.translapptesttask.di.app.modules.TranslatorModule
import com.example.translapptesttask.di.app.providers.RetrofitServiceProvider
import com.example.translapptesttask.presentation.presenters.TranslatorPresenter
import dagger.Component

@Component(
    modules = [
        RetrofitServiceProvider::class,
        TranslatorInteractorsProvider::class,
        DictionaryProvider::class,
        TranslatorModule::class,
        AppModule::class
    ],
    dependencies = [TranslationDependencies::class]
)
@PerFeature
abstract class TranslatorComponent : TranslationApi {
    @Component.Builder
    interface Builder {
        fun build(): TranslatorComponent
        fun setTranslationDependencies(translDependencies: TranslationDependencies): Builder
    }
    abstract fun inject(presenter: TranslatorPresenter)

    companion object {
        fun initAndGet(translDependencies: TranslationDependencies): TranslatorComponent =
            DaggerTranslatorComponent.builder()
                .setTranslationDependencies(translDependencies)
                .build()
    }
}
