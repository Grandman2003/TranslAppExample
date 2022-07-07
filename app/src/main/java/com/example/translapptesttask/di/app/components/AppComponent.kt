package com.example.translapptesttask.di.components

import com.example.translapptesttask.di.app.modules.NavigationModule
import com.example.translapptesttask.di.modules.SubComponentsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SubComponentsModule::class,
        NavigationModule::class
    ]
)
interface AppComponent {
    fun translateComponent(): TranslatorComponent.Builder
}
