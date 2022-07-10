package com.example.translapptesttask.di.app.providers

import android.content.Context
import com.example.translapptesttask.data.databases.DictionaryDao
import com.example.translapptesttask.data.databases.DictionaryDatabase
import com.example.translapptesttask.data.repositories.DictionaryRepository
import com.example.translapptesttask.data.repositories.DictionaryRepositoyrImpl
import com.example.translapptesttask.di.DaoContext
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class DictionaryProvider {
    @Provides
    fun getDictionaryDao(@DaoContext context: Context): DictionaryDao =
        DictionaryDatabase.newInstance(context).dictionaryDao()

    @Provides
    @Inject
    fun getDictionaryRepoImpl(dao: DictionaryDao): DictionaryRepository = DictionaryRepositoyrImpl(dao)
}
