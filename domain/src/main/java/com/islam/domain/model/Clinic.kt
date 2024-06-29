package com.islam.domain.model

import java.io.Serializable

data class Clinic(
    val address: String? = null,
    val doctorAvailabilities: List<DoctorAvailability?>? = null,
    val id: Int? = null,
    val latitude: Double,
    val longitude: Double,
    val name: String? = null,
    val examination: Double,
    val followUp: Double,
    val phone: String? = null
): Serializable
