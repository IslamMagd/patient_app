package com.islam.patient.authentication.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.domain.model.AuthenticationResult
import com.islam.domain.model.Patient
import com.islam.domain.model.State
import com.islam.domain.usecase.AuthenticationUsecase
import com.islam.domain.usecase.PatientUseCase.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationUsecase: AuthenticationUsecase,
    private val addPatientUseCase: AddPatientUseCase
): ViewModel() {

    private val _signUpResult: MutableStateFlow<AuthenticationResult?> = MutableStateFlow(null)
    val signUpResult: StateFlow<AuthenticationResult?> = _signUpResult

    private val _patientState: MutableStateFlow<State<Patient?>?> = MutableStateFlow(null)
    val patientState: StateFlow<State<Patient?>?> = _patientState

    fun signUp(email: String,password: String){
        viewModelScope.launch {
            _signUpResult.value = authenticationUsecase.signUpWithEmailAndPassword(email,password)
        }
    }

    fun addPatient(patient: Patient){
        viewModelScope.launch {
            addPatientUseCase(patient).collect{
                _patientState.value = it
            }
        }
    }

}