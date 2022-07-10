package com.example.feature_favourite_impl.di.components

import com.example.feature_favourite_api.FavouriteFeatureAPI
import com.example.feature_favourite_impl.di.PerFeature
import com.example.feature_favourite_impl.di.modules.FavorNavigationModule
import com.example.feature_favourite_impl.di.modules.FavouriteModule
import com.example.feature_favourite_impl.di.providers.FavouritesInteractorProvider
import com.example.feature_favourite_impl.presentation.presenters.FavouritesPresenter
import com.example.feature_favourite_impl.presentation.view.favourites.FavouritesActivity
import dagger.Component

@Component(
    modules = [
        FavouriteModule::class,
        FavorNavigationModule::class,
        FavouritesInteractorProvider::class
    ],
    dependencies = [FavouriteDependencies::class]
)
@PerFeature
abstract class FavouriteComponent : FavouriteFeatureAPI {
    abstract fun inject(favouritesPresenter: FavouritesPresenter)
    abstract fun inject(favouritesActivity: FavouritesActivity)

    @Component.Builder
    interface Builder {
        fun build(): FavouriteComponent
        fun setFavouriteDependencies(favouritesDependencies: FavouriteDependencies): Builder
    }

    companion object {
        fun initAndGet(favouritesDependencies: FavouriteDependencies): FavouriteComponent =
            DaggerFavouriteComponent.builder()
                .setFavouriteDependencies(favouritesDependencies)
                .build()
    }
}
