package com.islam.domain.usecase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.UserProfileChangeRequest
import com.islam.domain.repo.AuthenticationRepo

class AuthenticationUsecase(private val authenticationRepo: AuthenticationRepo) {

    suspend fun loginWithEmailAndPassword(email: String, password: String) =
        authenticationRepo.loginWithEmailAndPassword(email,password)

    suspend fun signUpWithEmailAndPassword(email: String, password: String) =
        authenticationRepo.signUpWithEmailAndPassword(email, password)

    suspend fun signInwithGoogleAccount(account: GoogleSignInAccount) =
        authenticationRepo.signInWithGoogleAccount(account)

    suspend fun resetpassword(email: String) =
        authenticationRepo.resetPassword(email)

    suspend fun signOut()= authenticationRepo.signOut()

    suspend fun updateProfile(profileUpdates: UserProfileChangeRequest) =
        authenticationRepo.updateProfile(profileUpdates)

}




