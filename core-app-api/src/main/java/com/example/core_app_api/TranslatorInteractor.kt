package com.example.core_app_api

import com.example.core_app_api.models.TranslatedEntity
import com.example.core_app_api.models.TranslatedWord
import com.example.core_app_api.models.TranslationRequest
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface TranslatorInteractor {
    fun checkFavourites(favouriteWord: String): Single<Boolean>
    fun proceedTranslationRequest(traslationRequest: TranslationRequest): Maybe<TranslatedWord>
    fun getDictionaryWords(): Observable<TranslatedEntity>
    fun findSimilarInDictionary(partString: String): Observable<TranslatedEntity>
}
