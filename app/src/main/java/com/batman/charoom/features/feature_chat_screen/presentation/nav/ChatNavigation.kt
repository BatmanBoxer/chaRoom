package com.batman.charoom.features.feature_chat_screen.presentation.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.HdrWeak
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.HdrWeak
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.batman.charoom.features.feature_chat_screen.domain.model.HomeNavButtons
import com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat.ChatUiScreenRoute
import com.batman.charoom.features.feature_chat_screen.presentation.screens.home.HomeUiScreenRoute
import com.batman.charoom.features.feature_chat_screen.presentation.screens.search.SearchScreen
import com.batman.charoom.features.feature_profile.presentation.screens.nav.navigateToProfileScreen
import com.batman.charoom.rootNavigation.NavChatScreen
import com.batman.charoom.rootNavigation.NavChatScreenRoute
import com.batman.charoom.rootNavigation.NavHomeScreen
import com.batman.charoom.rootNavigation.NavSearchScreen
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction2

/**
 * Created by Pronay Sarker on 05/10/2024 (6:16 PM)
 */
fun NavGraphBuilder.chatScreenRoute(
    navController: NavController,
    navigateToProfileScreen: (String) -> Unit,
) {
    navigation<NavChatScreenRoute>(
        startDestination = NavHomeScreen
    ) {
        homeScreen(
            navigateToChatScreen = navController::navigateToChatScreen,
            navigateToProfileScreen = navController::navigateToSearchScreen
        )
        chatScreen(navigateToHomeScreen = navController::popBackStack)
        searchScreen(navigateToProfileScreen = navigateToProfileScreen)
    }
}

private fun NavGraphBuilder.homeScreen(
    navigateToChatScreen: (String) -> Unit,
    navigateToProfileScreen: (id: String) -> Unit
) {
    composable<NavHomeScreen>() {
        HomeScreenRoute(
            navigateToChatScreen = navigateToChatScreen,
            navigateToSearchScreen = navigateToProfileScreen
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

private fun NavGraphBuilder.searchScreen(
   navigateToProfileScreen:(id:String) -> Unit
){
    composable<NavSearchScreen> {
        SearchScreen(navigateToProfileScreen)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    navigateToChatScreen: (String) -> Unit,
    navigateToSearchScreen: (String) ->Unit
) {
    var currentActiveIndex by rememberSaveable { mutableIntStateOf(0) }
    var currentNavigationDrawerIndex by rememberSaveable { mutableStateOf(false) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text(text = "About Us") },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = { Icon(imageVector = Icons.Outlined.Info, contentDescription = null) }
                )
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Chat") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "")
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    getNavigationBarItems().forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = (index == currentActiveIndex),
                            onClick = { currentActiveIndex = index },
                            icon = {
                                Icon(
                                    imageVector = if (currentActiveIndex == index) item.focusedIcon else item.unfocusedIcon,
                                    contentDescription = "navigation icon"
                                )
                            },
                            label = { Text(text = item.name) },
                        )
                    }
                }
            }
        ) {
            HomeUiScreenRoute(
                modifier = modifier.padding(it),
                navigateToChatScreen = navigateToChatScreen,
                navigateToSearchScreen = navigateToSearchScreen
            )
        }
    }
}

fun getNavigationBarItems(): List<HomeNavButtons> {
    return listOf(
        HomeNavButtons(
            name = "Chats",
            focusedIcon = Icons.Filled.ChatBubble,
            unfocusedIcon = Icons.Outlined.ChatBubbleOutline
        ),
        HomeNavButtons(
            name = "AI",
            focusedIcon = Icons.Filled.HdrWeak,
            unfocusedIcon = Icons.Outlined.HdrWeak
        ),
        HomeNavButtons(
            name = "Settings",
            focusedIcon = Icons.Filled.Settings,
            unfocusedIcon = Icons.Outlined.Settings
        ),
    )
}

fun NavController.navigateToHomeScreen() {
    navigate(NavHomeScreen)
}

fun NavController.navigateToChatScreen(chaId: String) {
    navigate(NavChatScreen(chatId = chaId))
}
fun NavController.navigateToSearchScreen(name: String) {
    navigate(NavSearchScreen(name))
}


