package com.example.translapptesttask.domain

import android.app.Application
import com.example.translapptesttask.di.components.AppComponent
import com.example.translapptesttask.di.components.DaggerAppComponent
import com.example.translapptesttask.domain.translator.ProjectParameters
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class TranslatorApp: Application()