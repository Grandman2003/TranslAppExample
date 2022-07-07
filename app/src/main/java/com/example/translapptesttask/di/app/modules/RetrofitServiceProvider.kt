package com.example.translapptesttask.di.modules

import com.example.translapptesttask.data.net.interfaces.TranslateService
import com.example.translapptesttask.di.TranslURL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
class RetrofitServiceProvider {
    // TODO: Partially useless scopes, redesign.
    @Provides fun getRXRetrofitService(@TranslURL url: String): Retrofit =
        Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Inject
    @Provides fun getTranslationService(retrofit: Retrofit) =
        retrofit.create(TranslateService::class.java)
}
