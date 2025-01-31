package com.peal.spacestationapp.ui.login

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.peal.spacestationapp.Constants
import com.peal.spacestationapp.Constants.ERROR_TAG
import javax.inject.Inject


/**
 * Created by Peal Mazumder on 31/1/25.
 */

class GoogleAuthUiClient @Inject constructor() {
    suspend fun getIdToken(
        context: Context,
        hasFilter: Boolean = true,
    ): Result<String> {
        return try {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(hasFilter)
                .setServerClientId(Constants.WEB_CLIENT_ID)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = CredentialManager.create(context).getCredential(
                request = request,
                context = context
            )

            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)

            Result.success(googleIdTokenCredential.idToken)
        } catch (e: NoCredentialException) {
            Log.d(ERROR_TAG, e.message.orEmpty())
            if (hasFilter) {
                getIdToken(context, hasFilter = false)
            } else {
                Result.failure(e)
            }

        } catch (e: GetCredentialException) {
            Log.d(ERROR_TAG, e.message.orEmpty())
            Result.failure(e)
        }
    }
}