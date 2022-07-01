package com.example.feature_favourite_impl.start

import android.content.Context
import android.content.Intent
import com.example.feature_favourite_api.FavouriteStarter
import com.example.feature_favourite_impl.presentation.view.favourites.FavouritesActivity
import javax.inject.Inject


class FavouriteStarterImpl @Inject constructor(): FavouriteStarter {
    override fun start(context: Context) {
        val actIntent = Intent(context, FavouritesActivity::class.java)
        context.startActivity(actIntent)
    }

    override fun controledStart(context: Context): Intent
            = Intent(context, FavouritesActivity::class.java)
}