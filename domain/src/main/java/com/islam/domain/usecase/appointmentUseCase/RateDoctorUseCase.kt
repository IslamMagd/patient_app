package com.islam.domain.usecase.appointmentUseCase

import com.islam.domain.repo.AppointmentRepo
import javax.inject.Inject

class RateDoctorUseCase @Inject constructor(private val appointmentRepo: AppointmentRepo) {
    suspend operator fun invoke(
        doctorId: String,
        rate: Int
    ) = appointmentRepo.rateDoctor(doctorId, rate)
}