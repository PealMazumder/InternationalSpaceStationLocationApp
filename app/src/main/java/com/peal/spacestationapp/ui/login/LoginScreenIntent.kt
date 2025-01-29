package com.peal.spacestationapp.ui.login

import androidx.credentials.Credential


sealed interface LoginScreenIntent {
    data class OnSignInRequestResult(val credential: Credential) : LoginScreenIntent
}