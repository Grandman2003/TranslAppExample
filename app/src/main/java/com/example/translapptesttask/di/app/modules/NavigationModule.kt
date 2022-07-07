package com.example.translapptesttask.di.app.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class NavigationModule {
    @Provides
    fun getCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Inject
    fun getRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Inject
    fun getNavigationHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()
}
