package com.example.translapptesttask.domain.models

import com.example.translapptesttask.data.net.respmodels.TranslatedWord

class TranslationResponse(var translState: TranslState, var word: TranslatedWord? = null)