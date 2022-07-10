package com.example.core_app_api

import com.example.module_injector.BaseApi

interface TranslationApi : BaseApi {
    fun translateInteractor(): TranslatorInteractor
    fun startTranslator(): TranslatorStarter
}
