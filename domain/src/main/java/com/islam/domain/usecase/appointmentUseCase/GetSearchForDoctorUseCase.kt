package com.islam.domain.usecase.appointmentUseCase

import com.islam.domain.repo.AppointmentRepo
import javax.inject.Inject

class GetSearchForDoctorUseCase @Inject constructor(private val appointmentRepo: AppointmentRepo) {
    suspend operator fun invoke(name: String) = appointmentRepo.searchDcotorByName(name)
}