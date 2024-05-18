package com.islam.patient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.SpecialityResponse
import com.islam.domain.model.State
import com.islam.domain.usecase.appointmentUseCase.GetAllDoctorsUseCase
import com.islam.domain.usecase.appointmentUseCase.GetAllSpecialtyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentViewModel @Inject constructor(
    private val getAllDoctorskUseCase: GetAllDoctorsUseCase,
    val getAllSpecialtyUseCase: GetAllSpecialtyUseCase
    ) : ViewModel(){
        private val _doctorsMutableStatFlow: MutableStateFlow<State<DoctorResponse?>?>
        = MutableStateFlow(null)
        val doctorsStateFlow: StateFlow<State<DoctorResponse?>?> = _doctorsMutableStatFlow

        private val _specialitiesMutablseStateFlow: MutableStateFlow<State<SpecialityResponse?>?>
        = MutableStateFlow(null)
        val specialitiesStateFlow: StateFlow<State<SpecialityResponse?>?>
        = _specialitiesMutablseStateFlow


    fun getAllDoctors(){
        viewModelScope.launch {
            getAllDoctorskUseCase().collect{
                _doctorsMutableStatFlow.value = it
            }
        }
    }

    fun getAllSpecialities(){
        viewModelScope.launch {
            getAllSpecialtyUseCase().collect{
                _specialitiesMutablseStateFlow.value = it
            }
        }
    }
}