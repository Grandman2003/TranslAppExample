package com.example.translapptesttask.presentation.view.translator

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_app_api.models.TranslatedEntity
import com.example.translapptesttask.R
import com.example.translapptesttask.databinding.ActivityTranslateBinding
import com.example.translapptesttask.di.components.AppComponent
import com.example.translapptesttask.di.components.TranslatorComponent
import com.example.translapptesttask.presentation.adapters.DictionaryRecycleAdapter
import com.example.translapptesttask.presentation.adapters.ItemHelperDictionaryCallback
import com.example.translapptesttask.presentation.presenters.TranslatorPresenter
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TranslateActivity : MvpAppCompatActivity(), TranslatorView {
    val disposeBag: CompositeDisposable = CompositeDisposable()
    lateinit var binding: ActivityTranslateBinding
    @InjectPresenter lateinit var translPresenter: TranslatorPresenter
    @Inject lateinit var navHolder: NavigatorHolder
    @Inject lateinit var translationComponent: TranslatorComponent
    private val dictionaryAdapter: DictionaryRecycleAdapter =
        DictionaryRecycleAdapter()
    private val navigator = object : AppNavigator(this, -1) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppComponent.get().inject(this)
        translPresenter.apply { translationComponent.inject(this) }
        configuring()
    }

    fun configuring() {
        RxTextView.textChanges(binding.inputText)
            .debounce(500, TimeUnit.MILLISECONDS)
            .switchMap {
                when (it.isEmpty() || it.isBlank()) {
                    true -> Observable.empty()
                    else -> Observable.just(it)
                }
            }
            .switchIfEmpty {
                it.onComplete()
            }
            .subscribe({
                    inputText ->
                translPresenter.translateTextCommand(
                    inputText.toString(),
                    binding.fromLangSpinner.selectedItem.toString(),
                    binding.toLangSpinner.selectedItem.toString(),
                )
            }, {}, { translPresenter.emptyInputField() }).addToBag()

        RxTextView.textChanges(binding.searchDict)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe({
                translPresenter.findInDictionary(it.toString())
            }, {}).addToBag()

        binding.toFavouritesButton.setOnClickListener {
            translPresenter.toFavouriteScreen()
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
            ItemTouchHelper(ItemHelperDictionaryCallback(translPresenter))
                .attachToRecyclerView(this)
        }
    }

    @ProvidePresenter
    fun getTranslationPresenter(): TranslatorPresenter = TranslatorPresenter()

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }

    override fun onPause() {
        super.onPause()
        navHolder.removeNavigator()
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

    override fun setDeletingError() {
        Snackbar.make(binding.root, R.string.deleting_error, Snackbar.LENGTH_LONG).show()
    }

    override fun setDictionaryElements(entityList: List<TranslatedEntity>) {
        dictionaryAdapter.submitList(entityList)
    }

    override fun cleanOutputField() {
        binding.translatedText.text = ""
    }
}
