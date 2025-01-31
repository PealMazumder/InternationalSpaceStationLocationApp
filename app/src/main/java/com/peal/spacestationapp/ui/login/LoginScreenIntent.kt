package com.peal.spacestationapp.ui.login

import android.content.Context
import com.peal.spacestationapp.domain.model.AuthenticationType


sealed class LoginScreenIntent {
    data class OnSignInRequest(val authType: AuthenticationType, val context: Context) : LoginScreenIntent()
}