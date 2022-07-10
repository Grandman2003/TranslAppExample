package com.example.translapptesttask.di.app.providers

import com.example.translapptesttask.data.net.interfaces.TranslateService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
class RetrofitServiceProvider {
    @Inject
    @Provides fun getRXRetrofitService(url: String): Retrofit =
        Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Inject
    @Provides fun getTranslationService(retrofit: Retrofit) =
        retrofit.create(TranslateService::class.java)
}
