package com.example.feature_favourite_api

import android.content.Context
import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen

interface FavouriteStarter {
    fun startScreen(): ActivityScreen
    fun start(context: Context): Intent
}
