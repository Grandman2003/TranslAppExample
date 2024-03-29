package com.example.translapptesttask.data.net.models

import com.example.core_app_api.models.TranslatedEntity
import com.example.core_app_api.models.TranslatedWord

fun interface Transformer {
    fun getTranslatedEntity(translatedWord: TranslatedWord): TranslatedEntity
}
