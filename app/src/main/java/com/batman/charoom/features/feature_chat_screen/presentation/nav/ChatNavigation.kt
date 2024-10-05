package com.batman.charoom.features.feature_chat_screen.presentation.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat.ChatUiScreenRoute
import com.batman.charoom.features.feature_chat_screen.presentation.screens.home.HomeUiScreenRoute
import com.batman.charoom.rootNavigation.HomeRoute
import com.batman.charoom.rootNavigation.NavChatScreenRoure
import com.batman.charoom.rootNavigation.NavHomeScreen

/**
 * Created by Pronay Sarker on 05/10/2024 (6:16 PM)
 */
fun NavGraphBuilder.chatScreenRoute(
    navController: NavController
) {
    navigation(
        startDestination = NavChatScreenRoure.toString(),
        route = "route"
    ) {
        homeScreen(navigateToChatScreen = navController::navigateToChatScreen)

        chatScreen(navigateToHomeScreen = navController::popBackStack)
    }
}

private fun NavGraphBuilder.homeScreen(
    navigateToChatScreen: () -> Unit
) {
    composable(route = NavHomeScreen.toString()) {
        HomeUiScreenRoute(
            navigateToChatScreen = navigateToChatScreen
        )
    }
}

private fun NavGraphBuilder.chatScreen(
    navigateToHomeScreen: () -> Unit
) {
    composable(route = NavChatScreenRoure.toString()) {
        ChatUiScreenRoute(
            navigateToHomeScreen = navigateToHomeScreen
        )
    }
}

fun NavController.navigateToHomeScreen() {
    navigate(NavHomeScreen.toString())
}

fun NavController.navigateToChatScreen() {
    navigate(NavChatScreenRoure.toString())
}