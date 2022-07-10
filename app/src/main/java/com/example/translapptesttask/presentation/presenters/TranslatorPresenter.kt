package com.example.translapptesttask.presentation.presenters

import android.util.Log
import com.example.core_app_api.TranslatorInteractor
import com.example.core_app_api.models.TranslatedEntity
import com.example.feature_favourite_api.FavouriteFeatureAPI
import com.example.translapptesttask.presentation.view.translator.TranslatorView
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class TranslatorPresenter : MvpPresenter<TranslatorView>() {
    @Inject lateinit var router: Router
    @Inject lateinit var translatorInteractor: TranslatorInteractor
    @Inject lateinit var favouriteApi: FavouriteFeatureAPI

    private val disposeBag: CompositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateDictionary()
    }

    override fun attachView(view: TranslatorView?) {
        super.attachView(view)
        updateDictionary()
    }

    private fun updateDictionary() {
        Log.v("TranslPresenter", "Initialise Dictionary List")
        translatorInteractor.getDictionaryWords()
            .toList().subscribe { list ->
                viewState.setDictionaryElements(list)
                Log.v("TranslPresenter", "List size is${list.size}")
            }.addToBag()
    }

    fun translateTextCommand(
        textForTranslation: String,
        fromLanguage: String,
        toLanguage: String,
    ) {
        translatorInteractor
            .proceedTranslationRequest(textForTranslation, fromLanguage, toLanguage)
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterSuccess {
                translatorInteractor
                    .findSimilarInDictionary(it.text)
                    .toList().subscribe { singleList ->
                        viewState.setAsFavourite(singleList.get(0).isFavourite)
                    }.addToBag()
            }
            .subscribe(
                {
                    viewState.showTranslation(it?.meanings?.get(0)?.translation?.text ?: " ")
                    updateDictionary()
                },
                {
                    Log.e("Oh NO, Error", it.localizedMessage ?: "No message")
                    viewState.showRequsetError()
                }
            ).addToBag()
    }

    fun setAsFavourite(textFavouriteWord: String) {
        translatorInteractor.run {
            Log.d("FavWord", "In Presenter $textFavouriteWord")
            checkAndChangeFavourites(textFavouriteWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess { isFavourite ->
                    viewState.setAsFavourite(isFavourite)
                    updateDictionary()
                }.subscribe(
                    {},
                    { error ->
                        Log.e(".TranslatePresenter", error.localizedMessage, error)
                        viewState.showFavouriteError()
                    }
                ).addToBag()
        }
    }

    fun findInDictionary(text: String) {
        translatorInteractor.findSimilarInDictionary(text)
            .toList().subscribe { list ->
                viewState.setDictionaryElements(list)
            }.addToBag()
    }

    fun toFavouriteScreen() {
        router.navigateTo(favouriteApi.favouriteStarter().startScreen())
    }

    private fun Disposable.addToBag() {
        disposeBag.add(this)
    }

    fun emptyInputField() {
        viewState.cleanOutputField()
    }

    fun deleteFromDictionary(entity: TranslatedEntity) {
        translatorInteractor
            .deleteWordFromDictionary(entity)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateDictionary()
            },{

            }).addToBag()
    }
}
