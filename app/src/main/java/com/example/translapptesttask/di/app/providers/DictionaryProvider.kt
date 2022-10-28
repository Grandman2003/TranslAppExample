package com.example.translapptesttask.di.app.providers

import com.example.translapptesttask.data.repositories.DictionaryRepository
import com.example.translapptesttask.data.repositories.DictionaryRepositoyrImpl
import dagger.Binds
import dagger.Module

@Module
interface DictionaryProvider {
    @Binds
    fun getDictionaryRepo(dictImpl: DictionaryRepositoyrImpl): DictionaryRepository
}
