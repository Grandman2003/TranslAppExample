package com.example.feature_favourite_impl.data.repos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteTranslatedEntity(
    val text: String,
    val translation: String,
    val fromLang: String,
    val toLang: String,
    val partOfSpeech: String= ""
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}