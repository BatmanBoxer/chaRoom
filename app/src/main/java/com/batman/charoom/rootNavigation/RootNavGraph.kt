package com.batman.charoom.rootNavigation

import androidx.compose.animation.EnterTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.batman.charoom.features.feature_chat_screen.presentation.nav.chatScreenRoute
import com.batman.charoom.features.feature_chat_screen.presentation.nav.navigateToHomeScreen
import com.batman.charoom.features.features_authentication.presentation.nav.authNavRoute

@Composable
fun RootNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavAuthRoute,
        enterTransition = { EnterTransition.None },
    ) {
        authNavRoute(
            navController = navController,
            navigateToHomeScreen = navController::navigateToHomeScreen
        )

        chatScreenRoute(
            navController = navController,
        )
    }
}