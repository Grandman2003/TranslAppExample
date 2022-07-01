package com.example.translapptesttask.domain.translator

import com.example.translapptesttask.data.net.interfaces.TranslateService
import com.example.translapptesttask.data.net.respmodels.TranslatedWord
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TranslatorInteractorImpl @Inject constructor(
    val translService: TranslateService,
): TranslatorInteractor {
    override fun getTextTranslation(textForTransl: String): Maybe<TranslatedWord> =
       translService.getTranslation(textForTransl)
           .subscribeOn(Schedulers.io())
           .flatMapMaybe { Maybe.just(it.get(0)) }

    override fun addToFavourites() {
        TODO("Not yet implemented")
    }

    override fun addTodictionary() {
        TODO("Not yet implemented")
    }

}
