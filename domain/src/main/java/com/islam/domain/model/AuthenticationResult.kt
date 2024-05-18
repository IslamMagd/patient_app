package com.islam.domain.model

data class AuthenticationResult(
    val isAuthenticated: Boolean,
    val errorMessage: String? = null
)
