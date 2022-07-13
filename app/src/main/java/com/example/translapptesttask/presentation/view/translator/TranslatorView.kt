package com.example.translapptesttask.presentation.view.translator

import com.example.core_app_api.models.TranslatedEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface TranslatorView : MvpView {
    @AddToEndSingle
    fun showTranslation(resultText: String)

    @OneExecution
    fun showRequestError()

    @OneExecution
    fun showFavouriteError()

    @OneExecution
    fun setAsFavourite(isFavourite: Boolean)

    @OneExecution
    fun setDeletingError()

    @AddToEndSingle
    fun setDictionaryElements(entityList: List<TranslatedEntity>)

    @OneExecution
    fun cleanOutputField()
}
