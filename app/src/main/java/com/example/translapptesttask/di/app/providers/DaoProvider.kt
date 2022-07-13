package com.example.translapptesttask.di.app.providers

import android.content.Context
import com.example.translapptesttask.data.databases.DictionaryDao
import com.example.translapptesttask.data.databases.DictionaryDatabase
import dagger.Module
import dagger.Provides

@Module
class DaoProvider {
    @Provides
    fun getDictionaryDao(context: Context): DictionaryDao =
        DictionaryDatabase.newInstance(context).dictionaryDao()
}
