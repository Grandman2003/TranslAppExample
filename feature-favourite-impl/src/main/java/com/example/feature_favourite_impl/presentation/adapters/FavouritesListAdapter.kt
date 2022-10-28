package com.example.feature_favourite_impl.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.core_app_api.models.TranslatedEntity
import com.example.feature_favourite_impl.databinding.FavouriteItemViewBinding

class FavouritesListAdapter :
    ListAdapter<TranslatedEntity, FavouriteElementHolder>(DIFF_UTIL_ITEM_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteElementHolder =
        FavouriteElementHolder(
            FavouriteItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FavouriteElementHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private companion object {
        val DIFF_UTIL_ITEM_CALLBACK = object : DiffUtil.ItemCallback<TranslatedEntity>() {
            override fun areItemsTheSame(
                oldItem: TranslatedEntity,
                newItem: TranslatedEntity
            ): Boolean = (oldItem.text == newItem.text)

            override fun areContentsTheSame(
                oldItem: TranslatedEntity,
                newItem: TranslatedEntity
            ): Boolean = (oldItem == newItem)
        }
    }
}
