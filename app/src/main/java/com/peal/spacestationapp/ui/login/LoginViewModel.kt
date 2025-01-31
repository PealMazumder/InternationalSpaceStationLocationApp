package com.peal.spacestationapp.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peal.spacestationapp.domain.model.AuthenticationType
import com.peal.spacestationapp.domain.usecases.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {
    private val _signInState = MutableStateFlow(LogInState())
    val signInState: StateFlow<LogInState> = _signInState


    fun onIntent(intent: LoginScreenIntent) {
        when (intent) {
            is LoginScreenIntent.OnSignInRequest -> {
                when (intent.authType) {
                    AuthenticationType.Google -> {
                        startGoogleSignIn(intent.context)
                    }
                }

            }
        }
    }

    private fun startGoogleSignIn(context: Context) {
        viewModelScope.launch {
            _signInState.update { it.copy(isLoginInProgress = true) }

            googleAuthUiClient.getIdToken(context, true)
                .onSuccess { idToken ->
                    authenticationUseCase.invoke(idToken)
                        .onSuccess {
                            _signInState.value = _signInState.value.copy(
                                isSignInSuccessful = true,
                                isLoginInProgress = false
                            )
                        }.onFailure {
                            _signInState.value = _signInState.value.copy(
                                isLoginInProgress = false
                            )
                        }

                }
                .onFailure {
                    _signInState.update { it.copy(isLoginInProgress = false) }
                }
        }
    }
}