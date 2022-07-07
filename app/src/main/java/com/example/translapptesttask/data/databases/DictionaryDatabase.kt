package com.example.translapptesttask.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core_app_api.models.TranslatedEntity

@Database(entities = [TranslatedEntity::class], version = 1)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
    companion object {
        private const val DATABASE_NAME = "dictDatabase"

        fun newInstance(context: Context): DictionaryDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                DictionaryDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}
