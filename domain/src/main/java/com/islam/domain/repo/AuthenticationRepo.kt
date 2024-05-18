package com.islam.domain.repo

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.UserProfileChangeRequest
import com.islam.domain.model.AuthenticationResult

interface AuthenticationRepo {
    suspend fun signUpWithEmailAndPassword(email: String,password: String): AuthenticationResult

    suspend fun loginWithEmailAndPassword(email: String,password: String): AuthenticationResult

    suspend fun signInWithGoogleAccount(account: GoogleSignInAccount): AuthenticationResult

    suspend fun resetPassword(email: String): AuthenticationResult

    suspend fun signOut(): AuthenticationResult

    suspend fun updateProfile(profileUpdates: UserProfileChangeRequest): AuthenticationResult
}