package com.peal.spacestationapp.domain.repository


/**
 * Created by Peal Mazumder on 30/1/25.
 */

interface AuthenticationRepository {
    suspend fun authenticateWithGoogle(idToken: String)
}