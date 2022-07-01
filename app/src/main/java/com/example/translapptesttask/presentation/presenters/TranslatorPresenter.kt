package com.example.translapptesttask.presentation.presenters

import android.util.Log
import com.example.translapptesttask.data.net.interfaces.TranslateService
import com.example.translapptesttask.domain.TranslatorApp
import com.example.translapptesttask.domain.translator.Screens
import com.example.translapptesttask.domain.translator.TranslatorInteractor
import com.example.translapptesttask.presentation.view.translator.TranslatorView
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class TranslatorPresenter : MvpPresenter<TranslatorView>() {
    @Inject lateinit var router: Router
    @Inject lateinit var translService: TranslateService
    @Inject lateinit var disposeBag: CompositeDisposable
    @Inject lateinit var translatorInteractor: TranslatorInteractor

    fun translateText(inputText: String){
        val disposable = translatorInteractor.getTextTranslation(inputText)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            { viewState.showTranslation(it.meanings[0].translation.text) },
               { Log.e("Oh NO, Error",it.localizedMessage ?: "No message") })
        disposeBag.add(disposable)
    }

    fun toFavouriteScreen() = router.navigateTo(Screens.favouriteScreen())

}