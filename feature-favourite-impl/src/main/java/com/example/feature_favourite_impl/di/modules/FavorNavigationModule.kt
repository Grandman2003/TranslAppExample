package com.example.feature_favourite_impl.di.modules

import com.example.feature_favourite_impl.di.PerFeature
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class FavorNavigationModule {
    @PerFeature
    @Provides
    fun getCicerone(): Cicerone<Router> = Cicerone.create()

    @PerFeature
    @Provides
    @Inject
    fun getRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @PerFeature
    @Provides
    @Inject
    fun getNavigationHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()
}
