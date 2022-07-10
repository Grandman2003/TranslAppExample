package com.example.feature_favourite_impl.di.components

import com.example.core_app_api.TranslationApi
import com.example.module_injector.BaseDependencies

interface FavouriteDependencies : BaseDependencies {
    fun translApplication(): TranslationApi
}
