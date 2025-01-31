package com.peal.spacestationapp.ui.navigation

import androidx.navigation.NavHostController
import com.peal.spacestationapp.ui.login.LoginNavigationEvent


/**
 * Created by Peal Mazumder on 30/1/25.
 */

class LoginNavigation(private val navController: NavHostController) {
    fun onNavigation(loginNavigationEvent: LoginNavigationEvent) {
        when (loginNavigationEvent) {
            is LoginNavigationEvent.OnNavigateHome -> {
                navController.navigate(Screens.HomeScreen) {
                    popUpTo(Screens.LoginScreen) { inclusive = true }
                }
            }
        }
    }
}