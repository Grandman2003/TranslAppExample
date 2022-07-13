package com.example.core_app_api

import com.example.core_app_api.models.TranslatedEntity
import com.example.core_app_api.models.TranslatedWord
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface TranslatorInteractor {
    fun checkAndChangeFavourites(favouriteWord: String): Single<TranslatedEntity>
    fun deleteFavouriteFromDictionary(entity: TranslatedEntity): Completable
    fun proceedTranslationRequest(
        textForTranslation: String,
        fromLanguage: String,
        toLanguage: String,
    ): Single<TranslatedWord>
    fun getDictionaryWords(): Observable<TranslatedEntity>
    fun findSimilarInDictionary(partString: String): Observable<TranslatedEntity>
    fun deleteWordFromDictionary(entity: TranslatedEntity): Completable
    fun addWordToDictionary(translatedWord: TranslatedWord): Completable
}
