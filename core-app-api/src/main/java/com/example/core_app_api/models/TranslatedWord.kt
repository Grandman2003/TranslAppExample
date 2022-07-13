package com.example.core_app_api.models

data class TranslatedWord(var text: String, val meanings: List<WordMeaning>){
    data class WordMeaning(val partOfSpeechCode: String, val translation: Translation){
        data class Translation(val text: String)
    }
}
