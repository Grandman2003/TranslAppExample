package com.example.translapptesttask.presentation.view.translator

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.translapptesttask.R
import com.example.core_app_api.models.TranslatedEntity
import com.example.translapptesttask.databinding.ActivityTranslateBinding
import com.example.translapptesttask.di.app.modules.FavouritesModule
import com.example.translapptesttask.domain.TranslatorApp
import com.example.core_app_api.models.TranslationRequest
import com.example.translapptesttask.presentation.adapters.DictionaryRecycleAdapter
import com.example.translapptesttask.presentation.presenters.TranslatorPresenter
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TranslateActivity : MvpAppCompatActivity(), TranslatorView {
    // TODO: rename dirs - got it
    // TODO: Interactors layer - got it (in process)
    // TODO: Switchmap - request filter(cancel previous)
    // TODO: Per screen scopes only(presenters) - got it(in process)
    // TODO: Add dictionary to app - third stage
    val disposeBag: CompositeDisposable = CompositeDisposable()
    lateinit var binding: ActivityTranslateBinding
    @InjectPresenter lateinit var translPresenter: TranslatorPresenter
    @Inject lateinit var router: Router
    @Inject lateinit var navHolder: NavigatorHolder
    private val dictionaryAdapter: DictionaryRecycleAdapter =
        DictionaryRecycleAdapter()

    private val navigator = object : AppNavigator(this, -1) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as TranslatorApp).translatorComponent.inject(this)
        configuring()
    }

    fun configuring() {
        RxTextView.textChanges(binding.inputText)
            .filter { !(it.isEmpty() || it.isBlank()) }
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe({
                    inputText ->
                translPresenter.translateTextCommand(
                    TranslationRequest(
                        inputText.toString(),
                        binding.fromLangSpinner.selectedItem.toString(),
                        binding.toLangSpinner.selectedItem.toString()
                    )
                )
            }, {}).addToBag()

        RxTextView.textChanges(binding.searchDict)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe({
                translPresenter.findInDictionary(it.toString())
            }, {}).addToBag()

        binding.toFavouritesButton.setOnClickListener {
            // translPresenter.toFavouriteScreen()
            // router.navigateTo(Screens.favouriteScreen())
            // router.replaceScreen(Screens.tranlsateScreen())
            startActivity(FavouritesModule.startStartFavouriteIntent(this))
        }

        with(binding.fromLangSpinner) {
            val adapterFrom: ArrayAdapter<*> = ArrayAdapter
                .createFromResource(context, R.array.languagesFrom, android.R.layout.simple_list_item_1)
            adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.adapter = adapterFrom
        }
        with(binding.toLangSpinner) {
            val adapterTo: ArrayAdapter<*> = ArrayAdapter
                .createFromResource(context, R.array.languagesTo, android.R.layout.simple_list_item_1)
            adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.adapter = adapterTo
        }

        binding.isItFavourite.setOnClickListener {
            translPresenter.setAsFavourite(binding.inputText.text.toString())
        }

        with(binding.dictList) {
            adapter = dictionaryAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    @ProvidePresenter
    fun getTranslationPresenter(): TranslatorPresenter = TranslatorPresenter().also {
        (application as TranslatorApp).translatorComponent.inject(it)
    }

    override fun onDestroy() {
        super.onDestroy()
        navHolder.removeNavigator()
        disposeBag.clear()
    }

    override fun onResume() {
        super.onResume()
        navHolder.setNavigator(navigator)
    }

    override fun showTranslation(resultText: String) {
        binding.translatedText.text = resultText
    }

    override fun showRequsetError() {
        Snackbar.make(binding.root, R.string.trans_error, Snackbar.LENGTH_LONG).show()
    }

    override fun showFavouriteError() {
        Snackbar.make(binding.root, R.string.fav_error, Snackbar.LENGTH_LONG).show()
    }

    fun Disposable.addToBag() {
        disposeBag.add(this)
    }

    override fun setAsFavourite(isFavourite: Boolean) {
        when (isFavourite) {
            false -> binding.isItFavourite.background = getDrawable(R.drawable.ic_baseline_star_off)
            true -> binding.isItFavourite.background = getDrawable(R.drawable.ic_baseline_star_on)
        }
    }

    override fun setDictionaryElements(entityList: List<TranslatedEntity>) {
        dictionaryAdapter.submitList(entityList)
    }
}
