package com.example.translapptesttask.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class DisposeProvider{
    @Provides
    fun getDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}