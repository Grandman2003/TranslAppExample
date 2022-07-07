package com.example.translapptesttask.data.repositories

import android.util.Log
import com.example.translapptesttask.data.databases.DictionaryDao
import com.example.translapptesttask.data.net.respmodels.TransformerImpl
import com.example.core_app_api.models.TranslatedEntity
import com.example.core_app_api.models.TranslatedWord
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class DictionaryRepositoyrImpl(private val dao: DictionaryDao) : DictionaryRepository {

    override fun addToDictionary(translatedWord: TranslatedWord) {
        val entity = TransformerImpl.getTranslatedEntity(translatedWord)
        if (!dao.isWordInDictionary(entity.text).blockingGet()) dao.addToDictionaryTable(entity)
    }

    override fun getWordsFromRepo(): Observable<TranslatedEntity> {
        Log.v("DictRepo", "Getting words with dao")
        return dao.getAllWords()
            .flatMapObservable { Observable.fromIterable(it) }
            .subscribeOn(Schedulers.io())
    }

    override fun findCurrentWord(word: String): Single<TranslatedEntity> {
        Log.d("FavWord", "In Repo $word")
        return dao.findWordInDictionary(word)
    }


    override fun updateIsFavouriteState(entity: TranslatedEntity) {
        dao.updateFavouriteState(entity.text,entity.isFavourite)
    }
}
