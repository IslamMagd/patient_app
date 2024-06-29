package com.islam.patient.appointment.clinic

import com.islam.domain.model.DoctorAvailability

interface OnBookClickListener {
    fun onBookClick(doctorAvailability: DoctorAvailability)
}