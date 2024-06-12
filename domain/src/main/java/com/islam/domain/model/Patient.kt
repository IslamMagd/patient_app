package com.islam.domain.model

data class Patient(
    val userId: String? = null,
    val name: String? = null,
    val email: String,
    val gender: String? = null,
    val phoneNumber: String? = null
)
