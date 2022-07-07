package com.example.core_app_api

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

interface ApplicationAPI {
    fun ciceroneNavigation(): Cicerone<Router>
    fun translateInteractor(): TranslatorInteractor
}
