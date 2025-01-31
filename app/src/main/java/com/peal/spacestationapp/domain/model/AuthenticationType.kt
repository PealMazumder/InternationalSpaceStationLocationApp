package com.peal.spacestationapp.domain.model


/**
 * Created by Peal Mazumder on 31/1/25.
 */

sealed class AuthenticationType(val name: String) {
    data object Google : AuthenticationType("Google")
}
