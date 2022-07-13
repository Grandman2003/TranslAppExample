package com.example.translapptesttask.presentation.presenters

import com.example.core_app_api.TranslatorInteractor
import com.example.core_app_api.models.TranslatedEntity
import com.example.core_app_api.models.TranslatedWord
import com.example.feature_favourite_api.FavouriteFeatureAPI
import com.example.translapptesttask.presentation.view.translator.TranslatorView
import com.github.terrakok.cicerone.Router
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class TranslatorPresenter() : MvpPresenter<TranslatorView>() {
    @Inject lateinit var router: Router
    @Inject lateinit var translatorInteractor: TranslatorInteractor
    @Inject lateinit var favouriteApi: FavouriteFeatureAPI

    private val androidScheduler by lazy { AndroidSchedulers.mainThread() }
    private var testScheduler: Scheduler? = null
    private val uiScheduler: Scheduler
        get() = when (testScheduler == null) {
            true -> androidScheduler
            else -> testScheduler!!
        }

    private val disposeBag: CompositeDisposable = CompositeDisposable()

    constructor(
        router: Router,
        translatorInteractor: TranslatorInteractor,
        favouriteFeatureAPI: FavouriteFeatureAPI,
        scheduler: Scheduler
    ) : this() {
        this.router = router
        this.favouriteApi = favouriteFeatureAPI
        this.translatorInteractor = translatorInteractor
        this.testScheduler = scheduler
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateDictionary()
    }

    override fun attachView(view: TranslatorView?) {
        super.attachView(view)
        updateDictionary()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }

    private fun updateDictionary() {
        translatorInteractor.getDictionaryWords()
            .toList().subscribe { list ->
                viewState.setDictionaryElements(list)
            }.addToBag()
    }

    fun translateTextCommand(
        textForTranslation: String,
        fromLanguage: String,
        toLanguage: String,
    ) {
        translatorInteractor
            .proceedTranslationRequest(textForTranslation, fromLanguage, toLanguage)
            .observeOn(uiScheduler)
            .subscribe(
                {
                    addToDictionaryAndCheckFavourite(it)
                    updateDictionary()
                    viewState.showTranslation(it?.meanings?.get(0)?.translation?.text ?: " ")
                },
                {
                    viewState.showRequestError()
                }
            ).addToBag()
    }

    fun setAsFavourite(textFavouriteWord: String) {
        translatorInteractor.run {
            checkAndChangeFavourites(textFavouriteWord)
                .observeOn(uiScheduler)
                .subscribe(
                    { currentWord ->
                        setFavouriteStatus(currentWord)
                    },
                    { error ->
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
            .observeOn(uiScheduler)
            .subscribe({
                updateDictionary()
            }) {
                viewState.setDeletingError()
            }.addToBag()
    }

    private fun setFavouriteStatus(currentWord: TranslatedEntity) =
        translatorInteractor
            .deleteFavouriteFromDictionary(currentWord)
            .observeOn(uiScheduler)
            .subscribe {
                viewState.setAsFavourite(currentWord.isFavourite)
                updateDictionary()
            }.addToBag()

    private fun addToDictionaryAndCheckFavourite(currentWord: TranslatedWord) =
        translatorInteractor
            .addWordToDictionary(currentWord)
            .observeOn(uiScheduler)
            .subscribe {
                translatorInteractor
                    .findSimilarInDictionary(currentWord.text)
                    .toList().subscribe { singleList ->
                        viewState.setAsFavourite(singleList.get(0).isFavourite)
                        updateDictionary()
                    }.addToBag()
            }.addToBag()
}
