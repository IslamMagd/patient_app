package com.islam.domain.model

data class TimeSlot(
    val id: Int? = null,
    val day: String? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val available: Boolean? = null
)
