package com.batman.charoom.features.feature_profile.presentation.screens.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.batman.charoom.features.feature_profile.presentation.screens.profile.ProfileScreenRoute
import com.batman.charoom.rootNavigation.NavProfileRoute
import com.batman.charoom.rootNavigation.NavProfileScreen

fun NavGraphBuilder.profileNavRoute(
    navController: NavController,
    navigateToSignUpScreen:()->Unit
) {
    navigation<NavProfileRoute>(
        startDestination = NavProfileScreen
    ){
        composable<NavProfileScreen>(){
            ProfileScreenRoute(
                navigateBack = navController::popBackStack,
            )
        }
    }
}
fun NavController.navigateToProfileScreen() {
    navigate(NavProfileScreen)
}
