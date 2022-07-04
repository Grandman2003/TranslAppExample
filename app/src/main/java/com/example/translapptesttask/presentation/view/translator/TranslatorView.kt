package com.example.translapptesttask.presentation.view.translator

import com.example.translapptesttask.domain.models.TranslationRequest
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface TranslatorView: MvpView {
    @StateStrategyType(value = AddToEndStrategy::class)
    fun showTranslation(resultText: String)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showRequsetError()

    @StateStrategyType(value = AddToEndStrategy::class)
    fun setAsFavourite(isFavourite: Boolean)

}