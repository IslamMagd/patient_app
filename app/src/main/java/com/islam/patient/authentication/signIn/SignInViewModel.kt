package com.islam.patient.authentication.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.islam.domain.model.AuthenticationResult
import com.islam.domain.model.LoginRequest
import com.islam.domain.model.LoginResponse
import com.islam.domain.usecase.AuthenticationUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authenticationUsecase: AuthenticationUsecase
): ViewModel() {
    private val _signInRsult: MutableStateFlow<LoginResponse?> = MutableStateFlow(null)
    val signInResult: StateFlow<LoginResponse?> = _signInRsult

    private val _signInWithGoogleResult: MutableStateFlow<AuthenticationResult?> = MutableStateFlow(null)
    val signInWithGoogleResult: StateFlow<AuthenticationResult?> = _signInWithGoogleResult

    fun signInWithEmailAndPassword(loginRequest: LoginRequest){
        viewModelScope.launch {
            _signInRsult.value = authenticationUsecase.loginWithEmailAndPassword(loginRequest)
        }
    }

    fun signInwithGoogleResult(account: GoogleSignInAccount){
        viewModelScope.launch {
            _signInWithGoogleResult.value = authenticationUsecase.signInwithGoogleAccount(account)
        }
    }

}