package com.islam.domain.model

import java.io.Serializable

data class Doctor(
    val birthDate: String?,
    val clinics: List<Clinic?>?,
    val email: String?,
    val gender: String?,
    val imgPath: String?,
    val name: String?,
    val phoneNumber: String?,
    val profTitle: String?,
    val specialty: String?,
    val uid: String?
): Serializable