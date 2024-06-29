package com.islam.domain.repo

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.UserProfileChangeRequest
import com.islam.domain.model.AuthenticationResult
import com.islam.domain.model.LoginRequest
import com.islam.domain.model.LoginResponse
import com.islam.domain.model.Patient

interface AuthenticationRepo {
    suspend fun registerPatient(patient: Patient): AuthenticationResult

    suspend fun loginWithEmailAndPassword(loginRequest: LoginRequest): LoginResponse

    suspend fun signInWithGoogleAccount(account: GoogleSignInAccount): AuthenticationResult

    suspend fun resetPassword(email: String): AuthenticationResult

    suspend fun signOut(): AuthenticationResult

    suspend fun updateProfile(profileUpdates: UserProfileChangeRequest): AuthenticationResult
}