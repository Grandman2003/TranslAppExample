package com.example.feature_favourite_impl.di

import com.example.feature_favourite_api.FavouriteStarter
import com.example.feature_favourite_impl.start.FavouriteStarterImpl
import dagger.Binds
import dagger.Module

@Module
internal interface FavouriteModule {
    @Binds
    fun getFavouriteStarter(favStarterImpl: FavouriteStarterImpl): FavouriteStarter
}
