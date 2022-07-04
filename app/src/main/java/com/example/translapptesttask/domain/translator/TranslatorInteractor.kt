package com.example.translapptesttask.domain.translator

import com.example.translapptesttask.data.net.respmodels.TranslatedWord
import com.example.translapptesttask.domain.models.TranslationRequest
import com.example.translapptesttask.domain.models.TranslationResponse
import io.reactivex.Maybe


interface TranslatorInteractor {
    fun getTextTranslation(textForTransl: String): Maybe<TranslatedWord>
    fun addToFavourites(translatedWord: TranslatedWord)
    fun addTodictionary(translatedWord: TranslatedWord)
    fun proceedTranslationRequest(traslationRequest: TranslationRequest): Maybe<TranslationResponse>
}