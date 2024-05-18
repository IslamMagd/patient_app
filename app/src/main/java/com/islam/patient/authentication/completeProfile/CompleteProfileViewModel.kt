package com.islam.patient.authentication.completeProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.UserProfileChangeRequest
import com.islam.domain.model.AuthenticationResult
import com.islam.domain.usecase.AuthenticationUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompleteProfileViewModel @Inject constructor(
    private val authenticationUsecase: AuthenticationUsecase
): ViewModel() {
    private val _updatePorfileMutableStateFlow: MutableStateFlow<AuthenticationResult?>
    = MutableStateFlow(null)
    val updatePorfileStateFlow: StateFlow<AuthenticationResult?> = _updatePorfileMutableStateFlow

     fun updateProfile(profileUpdates: UserProfileChangeRequest){
        viewModelScope.launch {
            _updatePorfileMutableStateFlow.value = authenticationUsecase.updateProfile(profileUpdates)
        }
    }

}