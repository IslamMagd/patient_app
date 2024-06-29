package com.islam.domain.model

data class BookingInput(
    val doctorId: String?,
    val clinicId: String?,
    val startTime: String?,
    val endTime: String?,
    val bookingDate: String?,
    val patientId: String?

)
