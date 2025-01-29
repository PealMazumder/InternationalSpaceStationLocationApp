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

class AccountSAuthenticationRepositoryImpl @Inject constructor() : AuthenticationRepository {
    override suspend fun authenticateWithGoogle(idToken: String) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        Firebase.auth.signInWithCredential(firebaseCredential).await()
    }
}