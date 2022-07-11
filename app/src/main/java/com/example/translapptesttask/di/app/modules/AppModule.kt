package com.example.translapptesttask.di.app.modules

import android.content.Context
import com.example.core_app_api.TranslationApi
import com.example.feature_favourite_api.FavouriteFeatureAPI
import com.example.feature_favourite_impl.di.components.FavouriteDependencies
import com.example.feature_favourite_impl.di.components.FavouritesComponentHolder
import com.example.translapptesttask.di.PerFeature
import com.example.translapptesttask.di.app.components.TranslationComponentHolder
import com.example.translapptesttask.di.app.components.TranslationDependencies
import com.example.translapptesttask.di.components.TranslatorComponent
import com.example.translapptesttask.domain.TranslatorApp
import com.example.translapptesttask.domain.models.ProjectParameters
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(): Context = TranslatorApp.appContext

    @Singleton
    @Provides
    @Inject
    fun provideTranslationComponentDependencies(router: Router): TranslationDependencies =
        object : TranslationDependencies {
            override fun siteUrl(): String = ProjectParameters.TRANSL_URL.getParameter()
            override fun router(): Router = router
        }

    @Singleton
    @Provides
    @Inject
    fun provideTranslatorFeatureComponent(dependencies: TranslationDependencies): TranslatorComponent {
        TranslationComponentHolder.init(dependencies)
        return TranslationComponentHolder.getComponent()
    }

    @PerFeature
    @Provides
    fun provideFavouriteComponentDependencies(): FavouriteDependencies =
        object : FavouriteDependencies {
            override fun translApplication(): TranslationApi = TranslationComponentHolder.get()
        }

    @PerFeature
    @Provides
    @Inject
    fun provideFavouritesApi(dependencies: FavouriteDependencies): FavouriteFeatureAPI {
        FavouritesComponentHolder.init(dependencies)
        return FavouritesComponentHolder.get()
    }
}
