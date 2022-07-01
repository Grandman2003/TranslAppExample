package com.example.feature_favourite_impl.di

import com.example.feature_favourite_api.FavouriteFeatureAPI
import com.example.feature_favourite_impl.presentation.view.favourites.FavouritesActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@Component(
    modules = [FavouriteModule::class,
              FavorNavigationModule::class],
    //dependencies = [FavouriteDependencies::class]
)
interface FavouriteComponent: FavouriteFeatureAPI {
    fun inject(favouriteActivity: FavouritesActivity)

    companion object{
//        private val daggerFavouriteComponentSingl: DaggerFavouriteComponent
//        = DaggerFavouriteComponent.builder().build() as DaggerFavouriteComponent
//
//        @Synchronized
//        fun initAndGet(): DaggerFavouriteComponent = daggerFavouriteComponentSingl

        @Volatile
        private var sFavouritesComponent: FavouriteComponent? = null

        // классический синглтон
        fun initAndGet(): FavouriteFeatureAPI? {
            if (sFavouritesComponent == null) {
                synchronized(FavouriteComponent::class.java) {
                    if (sFavouritesComponent == null) {
                        sFavouritesComponent = DaggerFavouriteComponent.builder().build()
                    }
                }
            }
            return sFavouritesComponent
        }
    }

}