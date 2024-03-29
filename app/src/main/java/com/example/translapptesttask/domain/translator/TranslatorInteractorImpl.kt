package com.example.translapptesttask.domain.translator

import com.example.core_app_api.TranslatorInteractor
import com.example.core_app_api.models.TranslatedEntity
import com.example.core_app_api.models.TranslatedWord
import com.example.translapptesttask.data.net.interfaces.TranslateService
import com.example.translapptesttask.data.repositories.DictionaryRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TranslatorInteractorImpl @Inject constructor(
    val translService: TranslateService,
    val dictionaryRepository: DictionaryRepository
) : TranslatorInteractor {

    override fun checkAndChangeFavourites(favouriteWord: String): Single<TranslatedEntity> {
        return dictionaryRepository
            .findCurrentWord(favouriteWord)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteFavouriteFromDictionary(entity: TranslatedEntity): Completable =
        dictionaryRepository.updateIsFavouriteState(
            entity.apply {
                this.isFavourite = !this.isFavourite
            }
        )

    override fun proceedTranslationRequest(
        textForTranslation: String,
        fromLanguage: String,
        toLanguage: String,
    ): Single<TranslatedWord> =
        translService
            .getTranslation(textForTranslation)
            .subscribeOn(Schedulers.io())
            .flatMap { Single.just(it.get(0)) }

    override fun getDictionaryWords(): Observable<TranslatedEntity> =
        dictionaryRepository.getWordsFromRepo()

    override fun findSimilarInDictionary(partString: String): Observable<TranslatedEntity> =
        getDictionaryWords().filter {
            it.translation.contains(partString) || it.text.contains(partString)
        }

    override fun deleteWordFromDictionary(entity: TranslatedEntity): Completable =
        dictionaryRepository.deleteFromDictionary(entity)

    override fun addWordToDictionary(translatedWord: TranslatedWord): Completable =
        dictionaryRepository.addToDictionary(translatedWord)
}
