package com.example.translapptesttask.di.app.modules

import com.example.core_app_api.ApplicationAPI
import com.example.core_app_api.TranslatorInteractor
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class ApplicationApiImpl @Inject constructor(
    private val interactor: TranslatorInteractor,
    private val cicerone: Cicerone<Router>
) : ApplicationAPI {

    override fun ciceroneNavigation() =
        cicerone

    override fun translateInteractor() =
        interactor
}
