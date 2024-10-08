package com.batman.charoom.features.feature_chat_screen.presentation.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat.ChatUiScreenRoute
import com.batman.charoom.features.feature_chat_screen.presentation.screens.home.HomeUiScreenRoute
import com.batman.charoom.rootNavigation.NavChatScreen
import com.batman.charoom.rootNavigation.NavChatScreenRoute
import com.batman.charoom.rootNavigation.NavHomeScreen

/**
 * Created by Pronay Sarker on 05/10/2024 (6:16 PM)
 */
fun NavGraphBuilder.chatScreenRoute(
    navController: NavController,
    navigateToProfileScreen:() -> Unit,
) {
    navigation<NavChatScreenRoute>(
        startDestination =NavHomeScreen
    ) {
        homeScreen(navigateToChatScreen = navController::navigateToChatScreen)

        chatScreen(navigateToHomeScreen = navController::popBackStack)
    }
}

private fun NavGraphBuilder.homeScreen(
    navigateToChatScreen: (String) -> Unit
) {
    composable<NavHomeScreen>() {
        HomeUiScreenRoute(
            navigateToChatScreen = navigateToChatScreen
        )
    }
}

private fun NavGraphBuilder.chatScreen(
    navigateToHomeScreen: () -> Unit
) {
    composable<NavChatScreen>() {
        ChatUiScreenRoute(
            navigateToHomeScreen = navigateToHomeScreen
        )
    }
}

fun NavController.navigateToHomeScreen() {
    navigate(NavHomeScreen)
}

fun NavController.navigateToChatScreen(chaId:String) {
    navigate(NavChatScreen(chatId = chaId))
}