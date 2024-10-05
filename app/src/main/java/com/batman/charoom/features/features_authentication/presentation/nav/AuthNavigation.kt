package com.batman.charoom.features.features_authentication.presentation.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.batman.charoom.features.features_authentication.presentation.screens.login_page.LoginScreenRoute
import com.batman.charoom.features.features_authentication.presentation.screens.signup_page.SignUpScreenRoute
import com.batman.charoom.rootNavigation.AuthNavRoute
import com.batman.charoom.rootNavigation.NavLogInScreen
import com.batman.charoom.rootNavigation.NavSignUpScreen

/**
 * Created by Pronay Sarker on 05/10/2024 (7:49 PM)
 */
fun NavGraphBuilder.authNavRoute(
    navController: NavController,
    navigateToHomeScreen: () -> Unit
) {
    navigation(
        startDestination = NavLogInScreen.toString(),
        route = AuthNavRoute.toString()
    ) {
        loginScreen(
            navigateToSignupScreen = navController::navigateToSignUpScreen,
            navigateToHomeScreen = navigateToHomeScreen
        )
        signupScreen(
            navigateBack = navController::popBackStack
        )
    }
}

private fun NavGraphBuilder.loginScreen(
    navigateToHomeScreen: () -> Unit,
    navigateToSignupScreen: () -> Unit
) {
    composable(
        route = NavLogInScreen.toString(),
        arguments = listOf(

        )
    ) {
        LoginScreenRoute(
            navigateToHomeScreen = navigateToHomeScreen,
            navigateToSignupScreen = navigateToSignupScreen
        )
    }
}

private fun NavGraphBuilder.signupScreen(
    navigateBack: () -> Unit
) {
    composable(
        route = NavSignUpScreen.toString(),
        arguments = listOf(

        )
    ) {
        SignUpScreenRoute {
            navigateBack()
        }
    }
}

fun NavController.navigateToSignUpScreen() {
    navigate(NavSignUpScreen.toString())
}