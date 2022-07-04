package com.example.translapptesttask.data.net.respmodels

object TransformerImpl: Transformer{
    override fun getTranslatedEntity(translatedWord: TranslatedWord): TranslatedEntity
        = TranslatedEntity(
            text = translatedWord.text,
            translation = translatedWord.meanings[0].translation.text,
            partOfSpeech = translatedWord.meanings[0].partOfSpeechCode,
            toLang = "",
            fromLang = ""
        )
}