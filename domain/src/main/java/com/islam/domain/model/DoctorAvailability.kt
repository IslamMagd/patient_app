package com.islam.domain.model

import java.io.Serializable

data class DoctorAvailability(
    val id: Int?,
    val day: String?,
    val startTime: String?,
    val endTime: String?,
    val available: Boolean? = null
): Serializable
