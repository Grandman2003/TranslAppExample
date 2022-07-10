package com.example.translapptesttask.start

import android.content.Intent
import com.example.core_app_api.TranslatorStarter
import com.example.translapptesttask.presentation.view.translator.TranslateActivity
import com.github.terrakok.cicerone.androidx.ActivityScreen
import javax.inject.Inject

class TranslatorStarterImpl @Inject constructor() : TranslatorStarter {
    override fun startTranslatorScreen(): ActivityScreen =
        ActivityScreen {
            Intent(it, TranslateActivity::class.java)
        }
}
