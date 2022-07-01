package com.example.feature_favourite_impl.presentation.view.favourites

import android.content.res.Resources
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.feature_favourite_impl.R
import com.example.feature_favourite_impl.databinding.ActivityFavouriteBinding
import com.example.feature_favourite_impl.databinding.ActivityFavouritesBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.appbar.AppBarLayout
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class FavouritesActivity:MvpAppCompatActivity() {
    //TODO:Cicerone Independence or module Injection?

    lateinit var binding: ActivityFavouriteBinding

    //@Inject lateinit var navHolder: NavigatorHolder
    private val navigator: Navigator = AppNavigator(this, R.id.contsr_fav)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onResume() {
        super.onResume()
       // navHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
       // navHolder.removeNavigator()
    }
}