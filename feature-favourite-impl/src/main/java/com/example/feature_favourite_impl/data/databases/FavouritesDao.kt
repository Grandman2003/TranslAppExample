package com.example.feature_favourite_impl.data.databases

import androidx.room.Dao
import androidx.room.Query
import com.example.feature_favourite_impl.data.repos.models.FavouriteTranslatedEntity

@Dao
interface FavouritesDao {
    @Query("SELECT*FROM favourites")
    fun getAllFavourites(): List<FavouriteTranslatedEntity>

    @Query("SELECT (CASE WHEN EXISTS(SELECT 1 FROM favourites" +
            "    WHERE  text= :word) THEN 1\n" +
            "    ELSE 0 END)")
    fun isWordInFavourites(word: String): Boolean
}