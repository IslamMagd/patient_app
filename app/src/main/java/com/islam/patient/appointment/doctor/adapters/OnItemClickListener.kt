package com.islam.patient.appointment.doctor.adapters

import com.islam.domain.model.Clinic
import com.islam.domain.model.Doctor

interface OnItemClickListener {
    fun onItemClick(doctor: Doctor)
}