package com.example.translapptesttask.di.app.modules

import android.content.Context
import com.example.translapptesttask.data.databases.DictionaryDao
import com.example.translapptesttask.data.databases.DictionaryDatabase
import com.example.translapptesttask.data.repositories.DictionaryRepositoyrImpl
import com.example.translapptesttask.di.DaoContext
import com.example.translapptesttask.presentation.adapters.DictionaryRecycleAdapter
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
    fun getDictionaryRepoImpl(dao: DictionaryDao) = DictionaryRepositoyrImpl(dao)
}
