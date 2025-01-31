package com.peal.spacestationapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.peal.spacestationapp.ui.home.HomeScreen
import com.peal.spacestationapp.ui.home.HomeViewModel
import com.peal.spacestationapp.ui.login.LoginScreen
import com.peal.spacestationapp.ui.login.LoginViewModel

/**
 * Created by Peal Mazumder on 30/1/25.
 */

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen
    ) {
        composable<Screens.LoginScreen> {
            val viewModel: LoginViewModel = hiltViewModel()
            val loginState by viewModel.signInState.collectAsStateWithLifecycle()
            val navigation = LoginNavigation(navController)
            LoginScreen(
                modifier,
                loginState,
                viewModel,
                onNavigation = {
                    navigation.onNavigation(it)
                }
            )
        }

        composable<Screens.HomeScreen> {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()
            HomeScreen(
                modifier,
                homeState,
                onEvent = { homeEvent ->
                    homeViewModel.handleHomeEvent(homeEvent)
                }
            )
        }
    }
}