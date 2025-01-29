package com.peal.spacestationapp.ui.login

import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.peal.spacestationapp.Constants.ERROR_TAG
import com.peal.spacestationapp.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    private val _signInState = MutableStateFlow(LogInState())
    val signInState: StateFlow<LogInState> = _signInState


    fun onIntent(intent: LoginScreenIntent) {
        when (intent) {
            is LoginScreenIntent.OnSignInRequestResult -> {
                onSignInWithGoogle(intent.credential)
            }
        }
    }

    private fun onSignInWithGoogle(credential: Credential) {
        viewModelScope.launch(CoroutineExceptionHandler { _, _ -> }) {
            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                authenticationRepository.authenticateWithGoogle(googleIdTokenCredential.idToken)
                _signInState.value = _signInState.value.copy(
                    isSignInSuccessful = true
                )
            } else {
                Log.d(ERROR_TAG, "Invalid credential type")
            }

        }
    }

}