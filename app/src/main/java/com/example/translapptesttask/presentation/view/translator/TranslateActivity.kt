package com.example.translapptesttask.presentation.view.translator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import com.example.translapptesttask.R
import com.example.translapptesttask.databinding.ActivityTranslateBinding
import com.example.translapptesttask.di.FeatureProxyInjector
import com.example.translapptesttask.di.Injector
import com.example.translapptesttask.presentation.presenters.TranslatorPresenter
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TranslateActivity :MvpAppCompatActivity(), TranslatorView {
    //TODO: rename dirs - got it
    //TODO: Interactors layer - got it (in process)
    //TODO: Switchmap - request filter(cancel previous)
    //TODO: Per screen scopes only(presenters) - got it(in process)
    //TODO: Add dictionary to app - third stage
    lateinit var binding: ActivityTranslateBinding
    @InjectPresenter lateinit var translPresenter: TranslatorPresenter
    @Inject lateinit var disposeBag: CompositeDisposable
    @Inject lateinit var router: Router
    @Inject lateinit var navHolder: NavigatorHolder


    private val navigator =object: AppNavigator(this, R.id.main_constr){
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Injector.translatorComponent.inject(this)
        configuring()
    }


    fun configuring(){
        disposeBag.add(RxTextView.textChanges(binding.inputText)
            .filter{!it.isEmpty()}
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe({
            inputText ->
                translPresenter.translateText(inputText?.toString() ?: "")
        },{

        }))
        binding.toFavouritesButton.setOnClickListener {
            //translPresenter.toFavouriteScreen()
            //router.navigateTo(Screens.favouriteScreen())
            //router.replaceScreen(Screens.tranlsateScreen())
            FeatureProxyInjector.getFavouriteFeature().favouriteStarter().start(this)
        }

        val adapterFrom: ArrayAdapter<*> = ArrayAdapter
            .createFromResource(this,R.array.languagesFrom, android.R.layout.simple_list_item_1)

        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapterTo: ArrayAdapter<*> = ArrayAdapter
            .createFromResource(this,R.array.languagesTo, android.R.layout.simple_list_item_1)

        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding.fromLangSpinner) {
            this.adapter = adapterFrom
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
        with(binding.toLangSpinner) {
            this.adapter = adapterTo
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        var tempBool: Boolean = false
        binding.favButton.setOnClickListener {
            when(tempBool){
                true -> binding.favButton.background = getDrawable(R.drawable.ic_baseline_star_off)
                false -> binding.favButton.background = getDrawable(R.drawable.ic_baseline_star_on)
            }
            tempBool= !tempBool
        }
    }

   @ProvidePresenter
   fun getTranslationPresenter(): TranslatorPresenter = TranslatorPresenter().also {
       Injector.translatorComponent.inject(it)
   }

    override fun onDestroy() {
        super.onDestroy()
       // navHolder.removeNavigator()
        disposeBag.clear()
    }

    override fun showTranslation(resultText: String) {
        binding.translatedText.text=resultText
    }

    override fun showRequsetError() {
        TODO("Not yet implemented")
    }

}