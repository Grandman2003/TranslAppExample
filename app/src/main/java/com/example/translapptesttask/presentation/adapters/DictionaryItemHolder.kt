package com.example.translapptesttask.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.core_app_api.models.TranslatedEntity
import com.example.translapptesttask.R
import com.example.translapptesttask.databinding.DictItemBinding

class DictionaryItemHolder(private var binding: DictItemBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var translatedEntity: TranslatedEntity

    fun onBind(item: TranslatedEntity) {
        this.translatedEntity = item
        binding.translHeaderText.text = item.translation
        binding.originHeaderText.text = item.text
        if (item.isFavourite)binding.isItFavourite.background =
            binding.root.context.getDrawable(R.drawable.ic_baseline_star_foc)
        binding.fromToLang.text = "en-rus"
    }
}
