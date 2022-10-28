package com.example.core_app_api.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class TranslatedEntity(
    val text: String,
    val translation: String,
    val fromLang: String,
    val toLang: String,
    val partOfSpeech: String = "",
    var isFavourite: Boolean = false
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
