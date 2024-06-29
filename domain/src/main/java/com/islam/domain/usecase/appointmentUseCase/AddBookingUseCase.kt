package com.islam.domain.usecase.appointmentUseCase

import com.islam.domain.model.BookingInput
import com.islam.domain.repo.AppointmentRepo
import javax.inject.Inject

class AddBookingUseCase @Inject constructor(private val appointmentRepo: AppointmentRepo) {
    suspend operator fun invoke(
        bookingInput: BookingInput
    ) = appointmentRepo.addBooking(bookingInput)
}