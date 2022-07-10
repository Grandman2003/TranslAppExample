package com.example.translapptesttask.presentation.view.translator

import com.example.core_app_api.models.TranslatedEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface TranslatorView : MvpView {
    @AddToEndSingle
    fun showTranslation(resultText: String)

    @Skip
    fun showRequsetError()

    @Skip
    fun showFavouriteError()

    @Skip
    fun setAsFavourite(isFavourite: Boolean)

    @Skip
    fun setDeletingError()

    @AddToEnd
    fun setDictionaryElements(entityList: List<TranslatedEntity>)

    @Skip
    fun cleanOutputField()
}
