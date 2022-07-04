package com.example.translapptesttask.data.net.respmodels

fun interface Transformer{
    fun getTranslatedEntity(translatedWord: TranslatedWord): TranslatedEntity
}