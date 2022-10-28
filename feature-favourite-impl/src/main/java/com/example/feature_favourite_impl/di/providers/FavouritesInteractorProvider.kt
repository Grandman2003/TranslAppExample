package com.example.feature_favourite_impl.di.providers

import com.example.core_app_api.TranslationApi
import com.example.feature_favourite_impl.domain.favourites.FavouritesInteractor
import com.example.feature_favourite_impl.domain.favourites.FavouritesInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class FavouritesInteractorProvider {
    @Provides
    @Inject
    fun getFavouriteInteractor(translationApi: TranslationApi): FavouritesInteractor =
        FavouritesInteractorImpl(translationApi)
}
