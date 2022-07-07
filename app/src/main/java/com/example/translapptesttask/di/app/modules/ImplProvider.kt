package com.example.translapptesttask.di.app.modules

import com.example.core_app_api.ApplicationAPI
import com.example.translapptesttask.data.repositories.DictionaryRepository
import com.example.translapptesttask.data.repositories.DictionaryRepositoyrImpl
import dagger.Binds
import dagger.Module

@Module
interface ImplProvider {
    @Binds
    fun getDictionaryRepository(DictionaryRepoImpl: DictionaryRepositoyrImpl): DictionaryRepository

    @Binds
    fun applicationApi(applicationApiImpl: ApplicationApiImpl): ApplicationAPI
}
