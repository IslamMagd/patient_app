package com.islam.patient.authentication.resetPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.domain.model.AuthenticationResult
import com.islam.domain.usecase.AuthenticationUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ResetPasswordViewModel @Inject constructor(private val authenticationUsecase: AuthenticationUsecase
): ViewModel() {

    private val _restpasswordRsult: MutableStateFlow<AuthenticationResult?> = MutableStateFlow(null)
    val restpasswordRsult: StateFlow<AuthenticationResult?> = _restpasswordRsult

        fun resetPassword(email: String){
            viewModelScope.launch {
                _restpasswordRsult.value = authenticationUsecase.resetpassword(email)
            }
        }

}