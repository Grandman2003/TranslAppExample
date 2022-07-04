package com.example.translapptesttask.data.repositories

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.translapptesttask.data.databases.DictionaryDao
import com.example.translapptesttask.data.net.respmodels.TranslatedEntity

@Database(entities = [TranslatedEntity::class], version = 1)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}