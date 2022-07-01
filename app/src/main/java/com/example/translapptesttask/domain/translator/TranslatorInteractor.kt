package com.example.translapptesttask.domain.translator

import com.example.translapptesttask.data.net.respmodels.TranslatedWord
import io.reactivex.Maybe


interface TranslatorInteractor {
    fun getTextTranslation(textForTransl: String): Maybe<TranslatedWord>
    fun addToFavourites()
    fun addTodictionary()
}