package com.example.translapptesttask.di.components

import com.example.translapptesttask.di.app.modules.AppModule
import com.example.translapptesttask.di.app.modules.NavigationModule
import com.example.translapptesttask.domain.TranslatorApp
import com.example.translapptesttask.presentation.view.translator.TranslateActivity
import dagger.Component
import dagger.internal.Preconditions
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        NavigationModule::class
    ]
)
@Singleton
abstract class AppComponent {
    abstract fun inject(translatorApp: TranslatorApp)
    abstract fun inject(translateActivity: TranslateActivity)

    companion object {
        @Volatile
        private var instance: AppComponent? = null

        fun get(): AppComponent {
            return Preconditions.checkNotNull(
                instance,
                "AppComponent is not initialized yet. Call init first."
            )!!
        }

        fun init(component: AppComponent) {
            require(instance == null) { "AppComponent is already initialized." }
            instance = component
        }
    }
}
