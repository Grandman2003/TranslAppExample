package com.example.translapptesttask.data.repositories

import com.example.core_app_api.models.TranslatedEntity
import com.example.core_app_api.models.TranslatedWord
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface DictionaryRepository {
    fun addToDictionary(translatedWord: TranslatedWord): Completable
    fun getWordsFromRepo(): Observable<TranslatedEntity>
    fun findCurrentWord(word: String): Single<TranslatedEntity>
    fun updateIsFavouriteState(entity: TranslatedEntity): Completable
    fun deleteFromDictionary(entity: TranslatedEntity): Completable
}
