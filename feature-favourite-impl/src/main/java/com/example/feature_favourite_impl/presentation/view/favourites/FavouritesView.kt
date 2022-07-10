package com.example.feature_favourite_impl.presentation.view.favourites

import com.example.core_app_api.models.TranslatedEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface FavouritesView : MvpView {
    @AddToEndSingle
    fun setupFavouriresEements(entities: List<TranslatedEntity>)

    @Skip
    fun deletingFavouriteError()
}
