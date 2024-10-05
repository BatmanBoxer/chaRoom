package com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batman.charoom.features.feature_chat_screen.domain.model.Chat

@Composable
fun ChatUiScreenRoute(
    viewModel: ChatScreenViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
){
    val uiState by viewModel.chatUiState.collectAsStateWithLifecycle()
    when(uiState){
        is ChatUiState.Error -> {}
        ChatUiState.Loading -> {}
        is ChatUiState.Success -> {
            val successState = uiState as ChatUiState.Success
            ChatScreen(successState.title,successState.chats)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(title:String,chats: List<Chat>){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) }
            )
        }
    ) {innerPadding->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(chats) { chat ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            horizontalArrangement = if (chat.isUser) Arrangement.End else Arrangement.Start
                        ) {
                            ChatTemplate(chat = chat)
                        }
                    }
                }
            }
    }
}
