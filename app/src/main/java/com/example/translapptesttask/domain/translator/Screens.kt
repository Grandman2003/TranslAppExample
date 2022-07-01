package com.example.translapptesttask.domain.translator

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.translapptesttask.di.FeatureProxyInjector
import com.example.translapptesttask.presentation.view.translator.TranslateActivity
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.ActivityScreen


object Screens{
  fun favouriteScreen() = object: ActivityScreen{
      override fun createIntent(context: Context): Intent
         = FeatureProxyInjector.getFavouriteFeature().favouriteStarter().controledStart(context)
  }
    fun translateScreen() = object: ActivityScreen{
        override fun createIntent(context: Context): Intent
                = Intent(context,TranslateActivity::class.java)
    }
}