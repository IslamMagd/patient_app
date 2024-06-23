package com.islam.patient.appointment.doctor.searchDoctor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.State
import com.islam.domain.usecase.appointmentUseCase.GetSearchForDoctorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDoctorViewModel @Inject constructor(private val getSearchForDoctorUseCase :GetSearchForDoctorUseCase): ViewModel() {
    private val _searchDoctorMutableStateFlow: MutableStateFlow<State<DoctorResponse?>?>
    = MutableStateFlow(null)
    val searchDoctorStateFlow: StateFlow<State<DoctorResponse?>?> = _searchDoctorMutableStateFlow

    fun getSearchDoctor(name: String){
        viewModelScope.launch {
            getSearchForDoctorUseCase(name).collect{
                _searchDoctorMutableStateFlow.value = it
            }
        }

    }
}