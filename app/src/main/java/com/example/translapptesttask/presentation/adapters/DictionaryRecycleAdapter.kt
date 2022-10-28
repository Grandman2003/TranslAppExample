package com.example.translapptesttask.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.core_app_api.models.TranslatedEntity
import com.example.translapptesttask.databinding.DictItemBinding

class DictionaryRecycleAdapter :
    ListAdapter<TranslatedEntity, DictionaryItemHolder>(DIFF_UTIL_ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryItemHolder =
        DictionaryItemHolder(
            DictItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DictionaryItemHolder, position: Int) {
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
