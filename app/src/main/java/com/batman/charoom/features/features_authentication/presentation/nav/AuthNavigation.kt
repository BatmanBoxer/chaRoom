package com.batman.charoom.features.features_authentication.presentation.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.batman.charoom.features.features_authentication.presentation.screens.login_page.LoginScreenRoute
import com.batman.charoom.features.features_authentication.presentation.screens.signup_page.SignUpScreenRoute
import com.batman.charoom.rootNavigation.NavAuthRoute
import com.batman.charoom.rootNavigation.NavLogInScreen
import com.batman.charoom.rootNavigation.NavSignUpScreen

/**
 * Created by Pronay Sarker on 05/10/2024 (7:49 PM)
 */
fun NavGraphBuilder.authNavRoute(
    navController: NavController,
    navigateToHomeScreen: () -> Unit
) {
    navigation<NavAuthRoute>(
        startDestination = NavLogInScreen
    ) {
        composable<NavLogInScreen>() {
            LoginScreenRoute(
                navigateToHomeScreen = navigateToHomeScreen,
                navigateToSignupScreen = navController::navigateToSignUpScreen
            )
        }
        composable<NavSignUpScreen>() {
            SignUpScreenRoute (
                navigateToLoginScreen = navController::popBackStack
            )
        }
    }
}


fun NavController.navigateToSignUpScreen() {
    navigate(NavSignUpScreen)
}