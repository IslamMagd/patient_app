package com.islam.patient.appointment.timeSlot

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.domain.model.BookingInput
import com.islam.domain.model.Clinic
import com.islam.domain.model.State
import com.islam.domain.model.TimeSlot
import com.islam.domain.usecase.appointmentUseCase.AddBookingUseCase
import com.islam.domain.usecase.appointmentUseCase.GetAvailableSlotsForDoctorUseCase
import com.islam.domain.usecase.appointmentUseCase.GetClinicsForDoctorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeSlotViewmodel  @Inject constructor(
    private val getTimeSlotsForDoctorUseCase: GetAvailableSlotsForDoctorUseCase,
    private val addBookingUseCase: AddBookingUseCase
) : ViewModel() {
    private val _timeSlotState: MutableStateFlow<State<List<TimeSlot>?>?> = MutableStateFlow(null)
    val timeSlotState : StateFlow<State<List<TimeSlot>?>?> = _timeSlotState

    private val _addBooking: MutableStateFlow<State<BookingInput?>?> = MutableStateFlow(null)
    private val addBookint : StateFlow<State<BookingInput?>?> = _addBooking

    fun getTimeSlotsForDoctor(doctorId: String,date: String){
        viewModelScope.launch {
            getTimeSlotsForDoctorUseCase(doctorId,date).collect{
                _timeSlotState.value = it
            }
        }
    }

    fun addBooking(bookingInput: BookingInput){
        viewModelScope.launch {
            addBookingUseCase(bookingInput).collect{
                _addBooking.value = it
            }
        }
    }




}