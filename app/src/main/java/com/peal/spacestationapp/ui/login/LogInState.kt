package com.peal.spacestationapp.ui.login

import androidx.compose.runtime.Immutable


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@Immutable
data class LogInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
)
