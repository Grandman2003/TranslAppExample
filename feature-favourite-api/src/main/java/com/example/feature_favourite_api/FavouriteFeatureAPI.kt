package com.example.feature_favourite_api

import com.example.module_injector.BaseApi

interface FavouriteFeatureAPI : BaseApi {
    fun favouriteStarter(): FavouriteStarter
}
