package com.islam.patient.appointment.doctor.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.SpecialityResponse
import com.islam.domain.model.State
import com.islam.domain.usecase.appointmentUseCase.GetAllDoctorsUseCase
import com.islam.domain.usecase.appointmentUseCase.GetAllSpecialtyUseCase
import com.islam.domain.usecase.appointmentUseCase.GetAverageRatingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllDoctorskUseCase: GetAllDoctorsUseCase,
    private val getAllSpecialtyUseCase: GetAllSpecialtyUseCase,
    private val getAverageRatingUseCase: GetAverageRatingUseCase
    ) : ViewModel() {
    private val _doctorsMutableStatFlow: MutableStateFlow<State<DoctorResponse?>?> =
        MutableStateFlow(null)
    val doctorsStateFlow: StateFlow<State<DoctorResponse?>?> = _doctorsMutableStatFlow

    private val _specialitiesMutablseStateFlow: MutableStateFlow<State<SpecialityResponse?>?> =
        MutableStateFlow(null)
    val specialitiesStateFlow: StateFlow<State<SpecialityResponse?>?> =
        _specialitiesMutablseStateFlow

    private val _ratingState: MutableStateFlow<Double?> = MutableStateFlow(null)
    val ratingState: StateFlow<Double?> = _ratingState


    fun getAllDoctors(token: String) {
        viewModelScope.launch {
            getAllDoctorskUseCase(token).collect {
                _doctorsMutableStatFlow.value = it
            }
        }
    }

    fun getAllSpecialities() {
        viewModelScope.launch {
            getAllSpecialtyUseCase().collect {
                _specialitiesMutablseStateFlow.value = it
            }
        }
    }

    fun getAverageRating(doctorId: String) {
        viewModelScope.launch {
            try {
                _ratingState.value = getAverageRatingUseCase(doctorId)
            } catch (e: Exception) {
                Log.e("get rating", e.message.toString())
            }
        }
    }
}