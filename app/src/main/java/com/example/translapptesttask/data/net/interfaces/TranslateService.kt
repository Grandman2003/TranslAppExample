package com.example.translapptesttask.data.net.interfaces

import com.example.translapptesttask.data.net.respmodels.TranslatedWord
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslateService {
    @GET("api/public/v1/words/search")
    fun getTranslation(@Query(value = "search") search: String): Single<List<TranslatedWord>>
}