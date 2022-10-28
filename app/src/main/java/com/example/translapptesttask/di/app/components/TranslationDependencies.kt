package com.example.translapptesttask.di.app.components

import com.example.module_injector.BaseDependencies
import com.github.terrakok.cicerone.Router

interface TranslationDependencies : BaseDependencies {
    fun siteUrl(): String
    fun router(): Router
}
