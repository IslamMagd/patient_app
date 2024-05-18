package com.islam.data.repo

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.islam.domain.model.AuthenticationResult
import com.islam.domain.repo.AuthenticationRepo
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepoImp(private val auth: FirebaseAuth): AuthenticationRepo {
    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): AuthenticationResult {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            AuthenticationResult(result.user != null)
        } catch (e: Exception) {
            AuthenticationResult(false, e.message.toString())
        }
    }

    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): AuthenticationResult {
        return try {
            val result = auth.signInWithEmailAndPassword(email,password).await()
            AuthenticationResult(result.user != null)
        } catch (e: Exception) {
            AuthenticationResult(false, e.message.toString())
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