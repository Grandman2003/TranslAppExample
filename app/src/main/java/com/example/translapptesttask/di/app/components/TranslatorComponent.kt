package com.example.translapptesttask.di.components

import android.content.Context
import com.example.translapptesttask.di.DaoContext
import com.example.translapptesttask.di.TranslURL
import com.example.translapptesttask.di.app.modules.DictionaryProvider
import com.example.translapptesttask.di.app.modules.FavouritesModule
import com.example.translapptesttask.di.app.modules.ImplProvider
import com.example.translapptesttask.di.app.modules.TranslatorInteractorsProvider
import com.example.translapptesttask.di.modules.RetrofitServiceProvider
import com.example.translapptesttask.presentation.presenters.TranslatorPresenter
import com.example.translapptesttask.presentation.view.translator.TranslateActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        RetrofitServiceProvider::class,
        TranslatorInteractorsProvider::class,
        DictionaryProvider::class,
        FavouritesModule::class,
        ImplProvider::class
    ]
)
interface TranslatorComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): TranslatorComponent
        @BindsInstance fun withTranslURL(@TranslURL url: String): Builder
        @BindsInstance fun appContext(@DaoContext context: Context): Builder
    }
    fun inject(activity: TranslateActivity)
    fun inject(presenter: TranslatorPresenter)
}
