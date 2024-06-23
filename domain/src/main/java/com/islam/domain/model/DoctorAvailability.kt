package com.islam.domain.model

data class DoctorAvailability(
    val id: Int?,
    val day: String?,
    val startTime: String?,
    val endTime: String?,
    val available: Boolean? = null
)
