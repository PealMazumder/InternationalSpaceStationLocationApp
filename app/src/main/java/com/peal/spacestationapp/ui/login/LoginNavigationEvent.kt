package com.peal.spacestationapp.ui.login


/**
 * Created by Peal Mazumder on 30/1/25.
 */

sealed class LoginNavigationEvent {
    data object OnNavigateHome : LoginNavigationEvent()
}
