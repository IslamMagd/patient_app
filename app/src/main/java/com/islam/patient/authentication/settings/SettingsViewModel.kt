package com.islam.patient.authentication.settings

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
class SettingsViewModel @Inject constructor(private val authenticationUsecase: AuthenticationUsecase
): ViewModel() {
    private val _signOutRsultMutableStateFlow: MutableStateFlow<AuthenticationResult?> =
        MutableStateFlow(null)
    val signOtResultStateFlow: StateFlow<AuthenticationResult?> = _signOutRsultMutableStateFlow

    fun signOut(){
        viewModelScope.launch {
            _signOutRsultMutableStateFlow.value = authenticationUsecase.signOut()
        }
    }

}