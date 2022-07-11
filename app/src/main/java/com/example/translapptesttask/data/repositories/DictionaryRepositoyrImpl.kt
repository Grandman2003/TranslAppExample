package com.example.translapptesttask.data.repositories

import com.example.core_app_api.models.TranslatedEntity
import com.example.core_app_api.models.TranslatedWord
import com.example.translapptesttask.data.databases.DictionaryDao
import com.example.translapptesttask.data.net.models.TransformerImpl
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class DictionaryRepositoyrImpl(private val dao: DictionaryDao) : DictionaryRepository {

    override fun addToDictionary(translatedWord: TranslatedWord): Completable {
        val entity = TransformerImpl.getTranslatedEntity(translatedWord)
        return when (!dao.isWordInDictionary(entity.text).blockingGet()) {
            true -> dao.addToDictionaryTable(entity)
            else -> Completable.complete()
        }.subscribeOn(Schedulers.io())
    }

    override fun getWordsFromRepo(): Observable<TranslatedEntity> {
        return dao.getAllWords()
            .flatMapObservable { Observable.fromIterable(it) }
            .subscribeOn(Schedulers.io())
    }

    override fun findCurrentWord(word: String): Single<TranslatedEntity> {
        return dao.findWordInDictionary(word)
    }

    override fun updateIsFavouriteState(entity: TranslatedEntity): Completable =
        dao.updateFavouriteState(
            entity.text,
            entity.translation,
            entity.isFavourite
        ).subscribeOn(Schedulers.io())

    override fun deleteFromDictionary(entity: TranslatedEntity): Completable =
        dao.deleteFromDictionary(entity).subscribeOn(Schedulers.io())
}
