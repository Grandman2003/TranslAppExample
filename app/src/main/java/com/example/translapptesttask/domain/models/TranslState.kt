package com.example.translapptesttask.domain.models

sealed class TranslState {
    class FAILED: TranslState()
    class SUCCESS: TranslState()
}