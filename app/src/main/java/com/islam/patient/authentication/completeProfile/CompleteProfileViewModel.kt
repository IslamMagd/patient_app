package com.islam.patient.authentication.completeProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.UserProfileChangeRequest
import com.islam.domain.model.AuthenticationResult
import com.islam.domain.model.Patient
import com.islam.domain.model.State
import com.islam.domain.usecase.AuthenticationUsecase
import com.islam.domain.usecase.PatientUseCase.UpdatePatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompleteProfileViewModel @Inject constructor(
    private val authenticationUsecase: AuthenticationUsecase,
    private val updatePatientUseCase: UpdatePatientUseCase
): ViewModel() {
    private val _updatePorfileMutableStateFlow: MutableStateFlow<AuthenticationResult?>
    = MutableStateFlow(null)
    val updatePorfileStateFlow: StateFlow<AuthenticationResult?> = _updatePorfileMutableStateFlow

    private val _updatePatientState: MutableStateFlow<State<Patient?>?> = MutableStateFlow(null)
    val updatePatientState: StateFlow<State<Patient?>?> = _updatePatientState


     fun updateProfile(profileUpdates: UserProfileChangeRequest){
        viewModelScope.launch {
            _updatePorfileMutableStateFlow.value = authenticationUsecase.updateProfile(profileUpdates)
        }
    }

    fun updatePatient(patient: Patient, id: String){
        viewModelScope.launch {
            updatePatientUseCase(patient,id).collect{
                _updatePatientState.value = it
            }
        }
    }

}