package com.example.translapptesttask.di.components

import com.example.translapptesttask.di.TranslURL
import com.example.translapptesttask.di.app.modules.TranslatorInjectorsProvider
import com.example.translapptesttask.di.modules.DisposeProvider
import com.example.translapptesttask.di.modules.RetrofitServiceProvider
import com.example.translapptesttask.presentation.presenters.TranslatorPresenter
import com.example.translapptesttask.presentation.view.translator.TranslateActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [DisposeProvider::class,
    RetrofitServiceProvider::class,
    TranslatorInjectorsProvider::class])
interface TranslatorComponent {
    @Subcomponent.Builder
    interface Builder{
        fun build(): TranslatorComponent
        @BindsInstance fun withTranslURL(@TranslURL url: String): Builder
    }
    fun inject(activity: TranslateActivity)
    fun inject(presenter: TranslatorPresenter)
}
