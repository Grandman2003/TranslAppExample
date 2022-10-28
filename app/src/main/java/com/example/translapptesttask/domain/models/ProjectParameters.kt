package com.example.translapptesttask.domain.models

enum class ProjectParameters(val value: String) {
    TRANSL_URL("https://dictionary.skyeng.ru/") {
        override fun getParameter() = value
    },

    EMPTY_REQUEST("Nothing to show") {
        override fun getParameter(): String = value
    };

    abstract fun getParameter(): String
}
