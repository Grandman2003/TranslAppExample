package com.example.feature_favourite_impl.start

import android.content.Context
import android.content.Intent
import com.example.feature_favourite_api.FavouriteStarter
import com.example.feature_favourite_impl.presentation.view.favourites.FavouritesActivity
import com.github.terrakok.cicerone.androidx.ActivityScreen
import javax.inject.Inject

class FavouriteStarterImpl @Inject constructor() : FavouriteStarter {
    override fun startScreen(): ActivityScreen = ActivityScreen {
        Intent(it, FavouritesActivity::class.java)
    }

    override fun start(context: Context): Intent =
        Intent(context, FavouritesActivity::class.java)
}
