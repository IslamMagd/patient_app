package com.islam.domain.usecase.appointmentUseCase

import com.islam.domain.repo.AppointmentRepo
import javax.inject.Inject

class GetAllSpecialtyUseCase @Inject constructor(private val repo: AppointmentRepo) {
    suspend operator fun invoke() = repo.getAllSpecialty()
}