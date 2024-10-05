package com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat

import com.batman.charoom.features.feature_chat_screen.domain.model.Chat

sealed class ChatUiState {
    data object Loading : ChatUiState()
    data class Error(val e: String) : ChatUiState()
    data class Success(
        val title: String,
        val chats: List<Chat>
    ) : ChatUiState()
}