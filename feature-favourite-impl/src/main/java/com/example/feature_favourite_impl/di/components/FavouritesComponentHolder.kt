package com.example.feature_favourite_impl.di.components

import com.example.feature_favourite_api.FavouriteFeatureAPI
import com.example.module_injector.ComponentHolder

object FavouritesComponentHolder : ComponentHolder<FavouriteFeatureAPI, FavouriteDependencies> {
    private var favouriteComponent: FavouriteComponent? = null
    override fun init(dependencies: FavouriteDependencies) {
        if (favouriteComponent == null) {
            synchronized(FavouritesComponentHolder::class.java) {
                if (favouriteComponent == null) {
                    favouriteComponent = FavouriteComponent.initAndGet(dependencies)
                }
            }
        }
    }

    override fun get(): FavouriteFeatureAPI =
        getComponent()

    internal fun getComponent(): FavouriteComponent {
        checkNotNull(favouriteComponent) { "FavouritesFeatureComponent was not initialized!" }
        return favouriteComponent!!
    }

    override fun reset() {
        favouriteComponent = null
    }
}
