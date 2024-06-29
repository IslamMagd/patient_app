package com.islam.domain.model

data class LoginResponse(
    val success: Boolean,
    val token: String?,
    val message: String? = null
)
