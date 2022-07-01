package com.example.translapptesttask.di

import com.example.feature_favourite_api.FavouriteFeatureAPI
import com.example.feature_favourite_impl.di.FavouriteComponent

object FeatureProxyInjector {
    fun getFavouriteFeature(): FavouriteFeatureAPI = FavouriteComponent.initAndGet()!!
}