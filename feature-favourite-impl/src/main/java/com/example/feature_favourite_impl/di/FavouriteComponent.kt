package com.example.feature_favourite_impl.di

import com.example.core_app_api.ApplicationAPI
import com.example.feature_favourite_api.FavouriteFeatureAPI
import com.example.feature_favourite_impl.presentation.view.favourites.FavouritesActivity
import dagger.Component

@Component(
    modules = [
        FavouriteModule::class,
        FavorNavigationModule::class
    ],
    dependencies = [FavouriteDependencies::class]
)
abstract class FavouriteComponent : FavouriteFeatureAPI {
    abstract fun inject(favouriteActivity: FavouritesActivity)

    @Component.Builder
    interface Builder {
        fun build(): FavouriteComponent
        fun setFavouriteDependencies(favouritesDependencies: FavouriteDependencies): Builder
    }

    companion object {
        @Volatile
        private var sFavouritesComponent: FavouriteComponent? = null

        // классический синглтон
        fun initAndGet(favouritesDependencies: FavouriteDependencies): FavouriteFeatureAPI {
            if (sFavouritesComponent == null) {
                synchronized(FavouriteComponent::class.java) {
                    if (sFavouritesComponent == null) {
                        sFavouritesComponent = DaggerFavouriteComponent.builder()
                            .setFavouriteDependencies(favouritesDependencies)
                            .build()
                    }
                }
            }
            return sFavouritesComponent ?: throw Throwable("Component was not initialised")
        }

        fun initAndGet(): FavouriteFeatureAPI {
            if (sFavouritesComponent == null) {
                synchronized(FavouriteComponent::class.java) {
                    if (sFavouritesComponent == null) {
                        sFavouritesComponent = DaggerFavouriteComponent.builder()
                            .build()
                    }
                }
            }
            return sFavouritesComponent ?: throw Throwable("Component was not initialised")
        }
    }

    @Component(
        dependencies = [ApplicationAPI::class]
    )
    interface FavouriteDependenciesComponent : FavouriteDependencies {
        @Component.Builder
        interface Builder {
            fun build(): FavouriteDependenciesComponent
            fun translApplication(applicationAPI: ApplicationAPI): Builder
        }
    }
}
