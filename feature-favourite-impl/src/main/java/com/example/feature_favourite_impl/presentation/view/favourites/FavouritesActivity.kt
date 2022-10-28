package com.example.feature_favourite_impl.presentation.view.favourites

import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_app_api.models.TranslatedEntity
import com.example.feature_favourite_impl.R
import com.example.feature_favourite_impl.databinding.ActivityFavouriteBinding
import com.example.feature_favourite_impl.di.components.FavouriteComponent
import com.example.feature_favourite_impl.di.components.FavouritesComponentHolder
import com.example.feature_favourite_impl.presentation.adapters.FavouritesListAdapter
import com.example.feature_favourite_impl.presentation.adapters.MyItemTouchHelperCallback
import com.example.feature_favourite_impl.presentation.presenters.FavouritesPresenter
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class FavouritesActivity : MvpAppCompatActivity(), FavouritesView {
    lateinit var binding: ActivityFavouriteBinding
    lateinit var favouriteComponent: FavouriteComponent
    private val favouritesAdapter = FavouritesListAdapter()
    @InjectPresenter lateinit var favouritesPresenter: FavouritesPresenter
    @Inject lateinit var navHolder: NavigatorHolder

    private val navigator: Navigator = AppNavigator(this, -1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        favouriteComponent = FavouritesComponentHolder.getComponent()
            .also {
                it.inject(this)
                it.inject(favouritesPresenter)
            }
        configuring()
    }
    fun configuring() {
        with(binding.favouritesList) {
            adapter = favouritesAdapter
            layoutManager = LinearLayoutManager(context)
            ItemTouchHelper(MyItemTouchHelperCallback(favouritesPresenter))
                .attachToRecyclerView(this)
        }
        binding.backButton.setOnClickListener {
            favouritesPresenter.navigateToTranslator()
        }
    }

    @ProvidePresenter
    fun getFavoritePresenter() = FavouritesPresenter()

    override fun onResume() {
        super.onResume()
        navHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navHolder.removeNavigator()
    }

    override fun setupFavouriresEements(entities: List<TranslatedEntity>) {
        favouritesAdapter.submitList(entities)
    }

    override fun deletingFavouriteError() {
        Snackbar.make(binding.root, R.string.favour_delete_error, Snackbar.LENGTH_LONG).show()
    }
}
