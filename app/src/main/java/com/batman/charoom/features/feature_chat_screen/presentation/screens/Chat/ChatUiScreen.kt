package com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batman.charoom.features.feature_chat_screen.domain.model.Chat
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce

@Composable
fun ChatUiScreenRoute(
    viewModel: ChatScreenViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {
    val uiState by viewModel.chatUiState.collectAsStateWithLifecycle()
    when (uiState) {
        is ChatUiState.Error -> {}
        ChatUiState.Loading -> {}
        is ChatUiState.Success -> {
            val successState = uiState as ChatUiState.Success
            ChatScreen(
                successState.title,
                successState.chats,
                onMsgSend = {
                    viewModel.addChat(
                        chat = Chat(
                            primaryContent = it,
                            secondaryContent = null,
                            secondaryImg = null,
                            isUser = true,
                            primaryImg = null,
                            timestamp = null
                        )
                    )
                }, addChatlimit = {
                    viewModel.changeLimit()
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@Composable
fun ChatScreen(title: String, chats: List<Chat>, onMsgSend: (String) -> Unit,addChatlimit:()->Unit) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .debounce(1000)
            .collect { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull()
                if (lastVisibleItem?.index == chats.size - 1) {
                    Log.d("darwin koirala","pagination triggered")
                    addChatlimit()
                }
            }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, modifier = Modifier.clickable {
                    addChatlimit()
                }) }
            )
        },
        bottomBar = {
            ChatTextField(
                modifier = Modifier,
                onSend = {
                    onMsgSend(it)
                },
                onClickPhoto = {}
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                reverseLayout = true,
                state = listState,
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
