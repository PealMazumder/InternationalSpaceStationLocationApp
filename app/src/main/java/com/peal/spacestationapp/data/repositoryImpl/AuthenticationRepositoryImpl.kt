package com.peal.spacestationapp.data.repositoryImpl

import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.peal.spacestationapp.domain.repository.AuthenticationRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


/**
 * Created by Peal Mazumder on 30/1/25.
 */

class AuthenticationRepositoryImpl @Inject constructor() : AuthenticationRepository {
    override suspend fun authenticate(idToken: String): Result<Boolean> {
        return try {
            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = Firebase.auth.signInWithCredential(firebaseCredential).await()
            val user = authResult.user
            if (user != null) {
                Result.success(true)
            } else {
                Result.failure(Exception("Authentication failed, user is null"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}