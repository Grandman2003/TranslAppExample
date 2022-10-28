package com.example.translapptesttask.di.app.components

import com.example.core_app_api.TranslationApi
import com.example.feature_favourite_impl.di.components.FavouritesComponentHolder
import com.example.module_injector.ComponentHolder
import com.example.translapptesttask.di.PerFeature
import com.example.translapptesttask.di.components.TranslatorComponent

@PerFeature
object TranslationComponentHolder : ComponentHolder< TranslationApi, TranslationDependencies> {
    private var translComponent: TranslatorComponent? = null
    override fun init(dependencies: TranslationDependencies) {
        if (translComponent == null) {
            synchronized(FavouritesComponentHolder::class.java) {
                if (translComponent == null) {
                    translComponent =
                        TranslatorComponent.initAndGet(dependencies)
                }
            }
        }
    }

    override fun get(): TranslationApi {
        return getComponent()
    }

    internal fun getComponent(): TranslatorComponent {
        checkNotNull(translComponent) { "FavouritesFeatureComponent was not initialized!" }
        return translComponent!!
    }

    override fun reset() {
        translComponent = null
    }
}
