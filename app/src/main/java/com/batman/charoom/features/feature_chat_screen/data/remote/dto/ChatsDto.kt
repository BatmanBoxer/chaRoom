package com.batman.charoom.features.feature_chat_screen.data.remote.dto

import com.google.firebase.Timestamp

data class chatsDto(
    val chat: ChatDto,
    val latestMessage: String,
    val timestamp: Timestamp,
)