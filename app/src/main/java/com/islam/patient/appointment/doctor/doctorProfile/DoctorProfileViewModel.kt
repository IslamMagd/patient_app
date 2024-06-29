package com.islam.patient.appointment.doctor.doctorProfile

import android.media.Rating
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.domain.model.Clinic
import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.State
import com.islam.domain.usecase.appointmentUseCase.GetAllDoctorsUseCase
import com.islam.domain.usecase.appointmentUseCase.GetAllSpecialtyUseCase
import com.islam.domain.usecase.appointmentUseCase.GetClinicsForDoctorUseCase
import com.islam.domain.usecase.appointmentUseCase.RateDoctorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorProfileViewModel @Inject constructor(
    private val rateDoctorUseCase: RateDoctorUseCase
) : ViewModel() {
    private val _rateDoctorState: MutableStateFlow<State<Rating?>?> = MutableStateFlow(null)
    val rateDoctorState : StateFlow<State<Rating?>?> = _rateDoctorState

    fun setRateForDoctor(doctorId: String,rate: Int){
        viewModelScope.launch {
            rateDoctorUseCase(doctorId,rate).collect{
                _rateDoctorState.value = it
            }
        }
    }
}