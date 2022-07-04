package com.example.translapptesttask.presentation.presenters

import android.util.Log
import com.example.translapptesttask.data.net.interfaces.TranslateService
import com.example.translapptesttask.domain.TranslatorApp
import com.example.translapptesttask.domain.models.TranslState
import com.example.translapptesttask.domain.models.TranslationRequest
import com.example.translapptesttask.domain.models.TranslationResponse
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

    var tempBool: Boolean = false

    fun translateText(inputText: String){
        val disposable = translatorInteractor.getTextTranslation(inputText)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            { viewState.showTranslation(it.meanings[0].translation.text) },
               { Log.e("Oh NO, Error",it.localizedMessage ?: "No message") })
        disposeBag.add(disposable)
    }

    fun TranslateTextCommand(traslationRequest: TranslationRequest){
        val disposable = translatorInteractor.proceedTranslationRequest(traslationRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it.translState){
                        is TranslState.SUCCESS ->
                            viewState.showTranslation(it.word?.meanings?.get(0)?.translation?.text ?: " ")
                        is TranslState.FAILED ->
                            viewState.showRequsetError()
                    }
                },
                { Log.e("Oh NO, Error",it.localizedMessage ?: "No message") })
        disposeBag.add(disposable)
    }

    fun addToFavourite(){
        viewState.setAsFavourite(tempBool)
        tempBool= !tempBool
    }
    fun toFavouriteScreen() = router.navigateTo(Screens.favouriteScreen())

}