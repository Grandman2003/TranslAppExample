package com.example.translapptesttask.di.app.modules

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.translapptesttask.data.databases.DictionaryDao
import com.example.translapptesttask.data.repositories.DictionaryDatabase
import com.example.translapptesttask.di.DaoContext
import com.example.translapptesttask.presentation.adapters.DictionaryItemHolder
import com.example.translapptesttask.presentation.adapters.DictionaryRecycleAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class RoomProvider {
    @Provides
    fun getDictionaryDao(@DaoContext context: Context): DictionaryDao =
        Room.databaseBuilder(context,DictionaryDatabase::class.java,"dictDatabase")
            .build().dictionaryDao()

    @Provides
    @Inject
    fun getDictionaryAdapter(dictionaryDao: DictionaryDao):
            DictionaryRecycleAdapter= DictionaryRecycleAdapter(dictionaryDao)
}