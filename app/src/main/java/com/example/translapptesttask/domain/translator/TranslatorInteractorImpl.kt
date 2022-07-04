package com.example.translapptesttask.domain.translator

import com.example.translapptesttask.data.databases.DictionaryDao
import com.example.translapptesttask.data.net.interfaces.TranslateService
import com.example.translapptesttask.data.net.respmodels.TransformerImpl
import com.example.translapptesttask.data.net.respmodels.TranslatedWord
import com.example.translapptesttask.domain.models.TranslState
import com.example.translapptesttask.domain.models.TranslationRequest
import com.example.translapptesttask.domain.models.TranslationResponse
import com.example.translapptesttask.presentation.presenters.TranslatorPresenter
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TranslatorInteractorImpl @Inject constructor(
    val translService: TranslateService,
    val dictionaryDao: DictionaryDao
): TranslatorInteractor {
    override fun getTextTranslation(textForTransl: String): Maybe<TranslatedWord> =
       translService.getTranslation(textForTransl)
           .subscribeOn(Schedulers.io())
           .flatMapMaybe { Maybe.just(it.get(0)) }

    override fun addToFavourites(translatedWord: TranslatedWord) {
        TODO("Not yet implemented")
    }

    override fun addTodictionary(translatedWord: TranslatedWord) {
        val entity = TransformerImpl.getTranslatedEntity(translatedWord)
        if(!dictionaryDao.isWordInDictionary(entity.text)) dictionaryDao.addToDictionaryTable(entity)
    }

    override fun proceedTranslationRequest(
        traslationRequest: TranslationRequest): Maybe<TranslationResponse> =
        when{
            traslationRequest.langFrom==traslationRequest.langTo ->
                Maybe.just(TranslationResponse(TranslState.FAILED()))
            else ->
                translService
                    .getTranslation(traslationRequest.text)
                    .subscribeOn(Schedulers.io())
                    .flatMapMaybe { Maybe.just(TranslationResponse(TranslState.SUCCESS(),it.get(0))) }
                    .doOnSuccess {
                        addTodictionary(it.word!!)
                    }
        }

}
