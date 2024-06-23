package com.islam.patient.appointment.doctor.doctorProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.domain.model.Clinic
import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.State
import com.islam.domain.usecase.appointmentUseCase.GetAllDoctorsUseCase
import com.islam.domain.usecase.appointmentUseCase.GetAllSpecialtyUseCase
import com.islam.domain.usecase.appointmentUseCase.GetClinicsForDoctorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorProfileViewModel @Inject constructor(
    private val getClinicsForDoctorUseCase: GetClinicsForDoctorUseCase
) : ViewModel() {
    private val _clinicsState: MutableStateFlow<State<List<Clinic>?>?> = MutableStateFlow(null)
    val clinicsState : StateFlow<State<List<Clinic>?>?> = _clinicsState

    fun getClinicsForDoctor(userId: String){
        viewModelScope.launch {
            getClinicsForDoctorUseCase(userId).collect{
                _clinicsState.value = it
            }
        }
    }
}