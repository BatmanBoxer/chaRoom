package com.batman.charoom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat.ChatUiScreenRoute
import com.batman.charoom.features.features_authentication.presentation.screens.login_page.LoginScreenRoute
import com.batman.charoom.features.features_authentication.presentation.screens.signup_page.SignUpScreenRoute
import com.batman.charoom.features.feature_chat_screen.presentation.screens.home.HomeUiScreenRoute
import com.batman.charoom.navigation.NavHomeScreen
import com.batman.charoom.navigation.NavLogInScreen
import com.batman.charoom.navigation.NavChatScreen
import com.batman.charoom.navigation.NavSignUpScreen
import com.batman.charoom.ui.theme.ChaRoomTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChaRoomTheme {
                ChaRoom()
            }
        }
    }
}


@Composable
fun ChaRoom(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavChatScreen(),
        enterTransition = { EnterTransition.None },
    ) {
        composable<NavLogInScreen> {
            LoginScreenRoute(
                navigateToHomeScreen = { navController.navigate(NavHomeScreen) },
                navigateToSignupScreen = { navController.navigate(NavSignUpScreen) }
            )
        }

        composable<NavSignUpScreen> {
            SignUpScreenRoute(
                navigateToLoginScreen = { navController.navigate(NavLogInScreen) }
            )
        }

        composable<NavHomeScreen> {
            HomeUiScreenRoute()
        }
        composable<NavChatScreen> {
            ChatUiScreenRoute()
        }
    }
}
