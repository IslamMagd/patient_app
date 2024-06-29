package com.islam.domain.usecase.appointmentUseCase

import com.islam.domain.repo.AppointmentRepo
import javax.inject.Inject

class GetAllDoctorsUseCase @Inject constructor(private val repository: AppointmentRepo) {
    suspend operator fun invoke(token: String) = repository.getAllDoctors(token)
}