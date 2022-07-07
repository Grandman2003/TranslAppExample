package com.example.translapptesttask.domain.translator

import android.content.Context
import android.content.Intent
import com.example.translapptesttask.di.app.modules.FavouritesModule
import com.example.translapptesttask.presentation.view.translator.TranslateActivity
import com.github.terrakok.cicerone.androidx.ActivityScreen

object Screens {
    fun favouriteScreen(): ActivityScreen = FavouritesModule.getFavouriteStarterScreen()

    fun translateScreen() = object : ActivityScreen {
        override fun createIntent(context: Context): Intent =
            Intent(context, TranslateActivity::class.java)
    }
}
