package com.islam.domain.usecase.appointmentUseCase

import com.islam.domain.repo.AppointmentRepo
import javax.inject.Inject

class GetAvailableSlotsForDoctorUseCase @Inject constructor(private val repo: AppointmentRepo) {
    suspend operator fun invoke(
        doctorId: String,
        date: String
    ) = repo.getAvailableSlotsForDoctor(doctorId,date)
}