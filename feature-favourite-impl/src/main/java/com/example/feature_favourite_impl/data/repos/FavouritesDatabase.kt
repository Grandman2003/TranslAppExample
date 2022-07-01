package com.example.feature_favourite_impl.data.repos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_favourite_impl.data.databases.FavouritesDao
import com.example.feature_favourite_impl.data.repos.models.FavouriteTranslatedEntity

@Database(entities = [FavouriteTranslatedEntity::class], version = 1)
abstract class FavouritesDatabase: RoomDatabase() {
    abstract fun favouritesDao():FavouritesDao
}