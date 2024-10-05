package com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChatUiScreenRoute(
    viewModel: ChatScreenViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
){
    val uiState by viewModel.chatUiState.collectAsStateWithLifecycle()
    ChatScreen(uiState)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(uiState: ChatUiState){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat") }
            )
        }
    ) {innerPadding->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding).background(Color.Green)){

            }
    }
}