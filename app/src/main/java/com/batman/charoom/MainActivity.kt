package com.batman.charoom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.batman.charoom.common.component.ChaRoomTopAppBar
import com.batman.charoom.features.features_authentication.presentation.screens.login_page.LoginScreenRoute
import com.batman.charoom.features.features_authentication.presentation.screens.signup_page.SignUpScreenRoute
import com.batman.charoom.features.home.HomeUiScreenRoute
import com.batman.charoom.navigation.NavHomeScreen
import com.batman.charoom.navigation.NavLogInScreen
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
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Scaffold(
                    topBar = {
                        when {
                            currentDestination?.route?.contains("NavLogInScreen") == true -> {
                                ChaRoomTopAppBar(
                                    topBarTitle = "Login",
                                    onNavigateBack = { /*TODO*/ })
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        enterTransition = { EnterTransition.None },
                        startDestination = NavLogInScreen,
                        modifier = Modifier.padding(innerPadding)
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
                    }
                }
            }
        }
    }
}

