package com.example.feature_favourite_impl.presentation.view.favourites

import android.os.Bundle
import com.example.feature_favourite_impl.R
import com.example.feature_favourite_impl.databinding.ActivityFavouriteBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity

class FavouritesActivity : MvpAppCompatActivity() {
    // TODO:Cicerone Independence or module Injection?

    lateinit var binding: ActivityFavouriteBinding

    // @Inject lateinit var navHolder: NavigatorHolder
    private val navigator: Navigator = AppNavigator(this, R.id.contsr_fav)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onResume() {
        super.onResume()
        //navHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        // navHolder.removeNavigator()
    }
}
