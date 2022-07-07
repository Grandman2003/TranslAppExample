package com.example.translapptesttask.domain.translator

import android.util.Log
import com.example.core_app_api.TranslatorInteractor
import com.example.translapptesttask.data.net.interfaces.TranslateService
import com.example.core_app_api.models.TranslatedEntity
import com.example.core_app_api.models.TranslatedWord
import com.example.translapptesttask.data.repositories.DictionaryRepository
import com.example.core_app_api.models.TranslationRequest
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TranslatorInteractorImpl @Inject constructor(
    val translService: TranslateService,
    val dictionaryRepository: DictionaryRepository
) : TranslatorInteractor {

    override fun checkFavourites(favouriteWord: String): Single<Boolean> {
        Log.d("FavWord", "In Interactor $favouriteWord")
        return dictionaryRepository
            .findCurrentWord(favouriteWord)
            .map {
                dictionaryRepository.updateIsFavouriteState(
                    it.also { it.isFavourite = !it.isFavourite }
                )
                it.isFavourite
            }
    }
    override fun proceedTranslationRequest(
        traslationRequest: TranslationRequest
    ): Maybe<TranslatedWord> =
        translService.getTranslation(traslationRequest.text)
            .subscribeOn(Schedulers.io())
            .flatMapMaybe { Maybe.just(it.get(0)) }
            .doOnSuccess {
                dictionaryRepository.addToDictionary(it)
            }

    override fun getDictionaryWords(): Observable<TranslatedEntity> =
        dictionaryRepository.getWordsFromRepo()

    override fun findSimilarInDictionary(partString: String): Observable<TranslatedEntity> =
        getDictionaryWords().filter {
            it.translation.contains(partString) || it.text.contains(partString)
        }
}
