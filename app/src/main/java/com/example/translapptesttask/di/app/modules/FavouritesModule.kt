package com.example.translapptesttask.di.app.modules

import android.content.Context
import android.content.Intent
import com.example.core_app_api.ApplicationAPI
import com.example.feature_favourite_api.FavouriteFeatureAPI
import com.example.feature_favourite_impl.di.DaggerFavouriteComponent
import com.example.feature_favourite_impl.di.DaggerFavouriteComponent_FavouriteDependenciesComponent
import com.example.feature_favourite_impl.di.FavouriteComponent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class FavouritesModule {


//    private fun getFavouriteFeatureApi(): FavouriteFeatureAPI=
//        FavouriteComponent.initAndGet(
//            DaggerFavouriteComponent_FavouriteDependenciesComponent
//                .builder()
//                .translApplication(applicationAPI = ApplicationApiImpl())
//                .build()
//        )


    companion object {

        @Provides
        @Singleton
        fun getFavouriteStarterScreen(): ActivityScreen
            = FavouriteComponent.initAndGet().favouriteStarter().startScreen()

        @Provides
        @Singleton
        fun startStartFavouriteIntent(
            context:Context,
        ): Intent
            = FavouriteComponent.initAndGet().favouriteStarter().start(context)

    }
}
