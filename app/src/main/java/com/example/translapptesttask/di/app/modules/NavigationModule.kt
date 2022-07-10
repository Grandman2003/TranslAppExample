package com.example.translapptesttask.di.app.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class NavigationModule {
    @Singleton
    @Provides
    fun getCicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    @Inject
    fun getRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Singleton
    @Provides
    @Inject
    fun getNavigationHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()
}
