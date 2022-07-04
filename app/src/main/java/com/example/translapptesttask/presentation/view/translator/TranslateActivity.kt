package com.example.translapptesttask.presentation.view.translator

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.View
import android.view.translation.Translator
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.translapptesttask.R
import com.example.translapptesttask.databinding.ActivityTranslateBinding
import com.example.translapptesttask.di.FeatureProxyInjector
import com.example.translapptesttask.di.Injector
import com.example.translapptesttask.domain.TranslatorApp
import com.example.translapptesttask.domain.models.TranslationRequest
import com.example.translapptesttask.presentation.adapters.DictionaryItemHolder
import com.example.translapptesttask.presentation.adapters.DictionaryRecycleAdapter
import com.example.translapptesttask.presentation.presenters.TranslatorPresenter
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar
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
    @Inject lateinit var dictionaryAdapter: DictionaryRecycleAdapter


    private val navigator =object: AppNavigator(this, R.id.main_constr){
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as TranslatorApp).translatorComponent.inject(this)
        configuring()
    }


    fun configuring(){
        disposeBag.add(RxTextView.textChanges(binding.inputText)
            .filter{!(it.isEmpty() || it.isBlank())}
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe({
            inputText ->
                //translPresenter.translateText(inputText?.toString() ?: "")
                translPresenter.TranslateTextCommand(TranslationRequest(
                    binding.inputText.text?.toString() ?: "",
                    binding.fromLangSpinner.selectedItem.toString(),
                    binding.toLangSpinner.selectedItem.toString()
                ))
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
                ) {}
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        binding.isItFavourite.setOnClickListener {
            translPresenter.addToFavourite()
        }

        with(binding.dictList){
            adapter  = dictionaryAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

   @ProvidePresenter
   fun getTranslationPresenter(): TranslatorPresenter = TranslatorPresenter().also {
       (application as TranslatorApp).translatorComponent.inject(it)
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
        Snackbar.make(binding.root,R.string.lang_error,Snackbar.LENGTH_LONG).show()
    }

    override fun setAsFavourite(isFavourite: Boolean) {
        when(isFavourite){
            true -> binding.isItFavourite.background = getDrawable(R.drawable.ic_baseline_star_off)
            false -> binding.isItFavourite.background = getDrawable(R.drawable.ic_baseline_star_on)
        }
    }

}