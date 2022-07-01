package com.example.feature_favourite_api

import android.content.Context
import android.content.Intent

interface FavouriteStarter {
    fun start(context: Context)
    fun controledStart(context: Context): Intent
}