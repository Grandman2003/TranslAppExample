package com.example.feature_favourite_impl.presentation.adapters

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.core_app_api.models.TranslatedEntity
import com.example.feature_favourite_impl.databinding.FavouriteItemViewBinding

class FavouriteElementHolder(private var binding: FavouriteItemViewBinding) : ViewHolder(binding.root) {
    lateinit var entity: TranslatedEntity
    fun onBind(translatedEntity: TranslatedEntity) {
        this.entity = translatedEntity
        binding.originWord.text = translatedEntity.text
        binding.translatedWord.text = translatedEntity.translation
        binding.languages.text = "en-rus"
    }
}
