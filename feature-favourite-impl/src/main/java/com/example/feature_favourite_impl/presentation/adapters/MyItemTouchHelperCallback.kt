package com.example.feature_favourite_impl.presentation.adapters

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_favourite_impl.presentation.presenters.FavouritesPresenter

class MyItemTouchHelperCallback(private val presenter: FavouritesPresenter) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(0, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        presenter.removeFromFavouriteList((viewHolder as FavouriteElementHolder).entity)
    }

    override fun isItemViewSwipeEnabled(): Boolean = true
}
