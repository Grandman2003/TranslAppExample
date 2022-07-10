package com.example.feature_favourite_impl.presentation.presenters

import com.example.core_app_api.TranslationApi
import com.example.core_app_api.models.TranslatedEntity
import com.example.feature_favourite_impl.domain.favourites.FavouritesInteractor
import com.example.feature_favourite_impl.presentation.view.favourites.FavouritesView
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import javax.inject.Inject

class FavouritesPresenter : MvpPresenter<FavouritesView>() {
    val disposeBag = CompositeDisposable()
    @Inject lateinit var favouritesInteractor: FavouritesInteractor
    @Inject lateinit var router: Router
    @Inject lateinit var translationApi: TranslationApi

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setFavouriteList()
    }

    private fun setFavouriteList() {
        favouritesInteractor.getFavouritesList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setupFavouriresEements(it)
            }, {}).addtobag()
    }

    fun Disposable.addtobag() {
        disposeBag.add(this)
    }

    fun removeFromFavouriteList(entity: TranslatedEntity) {
        favouritesInteractor.deleteFromFavourites(entity)
            .subscribe({
                setFavouriteList()
            }) {
                viewState.deletingFavouriteError()
            }.addtobag()
    }

    fun navigateToTranslator() {
        router.navigateTo(translationApi.startTranslator().startTranslatorScreen())
    }
}
