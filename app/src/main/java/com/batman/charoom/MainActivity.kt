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
import com.batman.charoom.features.features_authentication.presentation.screens.login_page.LoginScreen
import com.batman.charoom.features.features_authentication.presentation.screens.login_page.LoginScreenTopAppBar
import com.batman.charoom.navigation.NavLogInScreen
import com.batman.charoom.ui.theme.ChaRoomTheme

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
                        when{
                            currentDestination?.route?.contains("NavLogInScreen") == true -> LoginScreenTopAppBar()
                        }
                    }
                ) {innerPadding->
                    NavHost(
                        navController = navController,
                        enterTransition = { EnterTransition.None },
                        startDestination = NavLogInScreen,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable<NavLogInScreen> {
                           LoginScreen()
                        }
                    }

                }

            }
        }
    }
}

