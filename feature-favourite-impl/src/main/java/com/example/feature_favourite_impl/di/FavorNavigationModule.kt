package com.example.feature_favourite_impl.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class FavorNavigationModule {
    @Provides
    fun getCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Inject
    fun getRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Inject
    fun getNavigationHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()
}