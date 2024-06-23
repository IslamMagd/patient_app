package com.islam.domain.model

import java.io.Serializable

data class Clinic(
    val address: String? = null,
    val doctorAvailabilities: List<DoctorAvailability?>? = null,
    val id: Int? = null,
    val length: Double? = null,
    val name: String? = null,
    val price: Double? = null
): Serializable
