package com.islam.domain.usecase.appointmentUseCase

import com.islam.domain.repo.AppointmentRepo
import javax.inject.Inject

class GetAverageRatingUseCase @Inject constructor(private val repo: AppointmentRepo) {
    suspend operator fun invoke(
        doctorId: String
    ) = repo.getAverageRating(doctorId)

}