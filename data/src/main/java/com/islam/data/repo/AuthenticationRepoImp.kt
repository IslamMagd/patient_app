package com.islam.data.repo

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.islam.data.remote.ServiceApi
import com.islam.domain.model.AuthenticationResult
import com.islam.domain.model.LoginRequest
import com.islam.domain.model.LoginResponse
import com.islam.domain.model.Patient
import com.islam.domain.repo.AuthenticationRepo
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepoImp(
    private val auth: FirebaseAuth,
    private val serviceApi: ServiceApi
): AuthenticationRepo {
    override suspend fun registerPatient(
        patient: Patient
    ): AuthenticationResult {
        return try {
            serviceApi.registerPatient(patient)
            AuthenticationResult(true)
        } catch (e: Exception) {
            AuthenticationResult(false, e.message.toString())
        }
    }

    override suspend fun loginWithEmailAndPassword(
        loginRequest: LoginRequest
    ): LoginResponse {
        return try {
             val result = serviceApi.loginWithEmailAndPassword(loginRequest)
            val token = result.body()?.token
           LoginResponse(
               true,
               token
           )
        } catch (e: Exception) {
            LoginResponse(false, e.message.toString())
        }
    }

    override suspend fun signInWithGoogleAccount(account: GoogleSignInAccount): AuthenticationResult {
       return try {
           val credntial = GoogleAuthProvider.getCredential(account.idToken,null)
            auth.signInWithCredential(credntial).await()
            AuthenticationResult(true)
        }catch (e: Exception) {
            AuthenticationResult(false, e.message.toString())
        }
    }

    override suspend fun resetPassword(email: String): AuthenticationResult {
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthenticationResult(true)
        }catch (e: Exception){
            AuthenticationResult(false,e.message.toString())
        }
    }

    override suspend fun signOut(): AuthenticationResult {
        return try {
            auth.signOut()
            AuthenticationResult(true)
        }catch (e: Exception){
            AuthenticationResult(false,e.message.toString())
        }
    }

    override suspend fun updateProfile(profileUpdates: UserProfileChangeRequest)
    : AuthenticationResult {
        return try {
            auth.currentUser?.updateProfile(profileUpdates)?.await()
            AuthenticationResult(true)
        }catch (e: Exception){
            AuthenticationResult(false,e.message.toString())
        }
    }
}