package com.islam.patient.appointment.timeSlot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.domain.model.Clinic
import com.islam.domain.model.State
import com.islam.domain.model.TimeSlot
import com.islam.domain.usecase.appointmentUseCase.GetAvailableSlotsForDoctorUseCase
import com.islam.domain.usecase.appointmentUseCase.GetClinicsForDoctorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeSlotViewmodel  @Inject constructor(
    private val getTimeSlotsForDoctorUseCase: GetAvailableSlotsForDoctorUseCase
) : ViewModel() {
    private val _timeSlotState: MutableStateFlow<State<List<TimeSlot>?>?> = MutableStateFlow(null)
    val timeSlot : StateFlow<State<List<TimeSlot>?>?> = _timeSlotState

    fun getClinicsForDoctor(doctorId: String,date: String){
        viewModelScope.launch {
            getTimeSlotsForDoctorUseCase(doctorId,date).collect{
                _timeSlotState.value = it
            }
        }
    }
}