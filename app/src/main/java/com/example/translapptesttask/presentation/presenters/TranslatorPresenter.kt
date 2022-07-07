package com.example.translapptesttask.presentation.presenters

import android.util.Log
import com.example.core_app_api.TranslatorInteractor
import com.example.core_app_api.models.TranslationRequest
import com.example.translapptesttask.domain.translator.Screens
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

    private val disposeBag: CompositeDisposable = CompositeDisposable()
    private var tempBool: Boolean = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
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

    fun translateTextCommand(traslationRequest: TranslationRequest) {
        translatorInteractor.proceedTranslationRequest(traslationRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.showTranslation(it?.meanings?.get(0)?.translation?.text ?: " ")
                    updateDictionary()
                    setAsFavourite(it.text)
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
            checkFavourites(textFavouriteWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { isFavourite ->
                        viewState.setAsFavourite(isFavourite)
                        updateDictionary()
                    },
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

    fun toFavouriteScreen() = router.navigateTo(Screens.favouriteScreen())

    private fun Disposable.addToBag() {
        disposeBag.add(this)
    }
}
