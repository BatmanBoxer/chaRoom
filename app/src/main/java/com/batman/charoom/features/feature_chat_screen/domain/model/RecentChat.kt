package com.batman.charoom.features.feature_chat_screen.domain.model

data class RecentChat(
    val name: String,
    val profileImageUrl: String,
    val lastMessage: String,
    val timestamp: String
)