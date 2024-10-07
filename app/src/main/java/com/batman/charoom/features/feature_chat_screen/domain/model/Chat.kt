package com.batman.charoom.features.feature_chat_screen.domain.model

import com.batman.charoom.features.feature_chat_screen.data.remote.dto.ChatDto
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Chat(
    val isUser: Boolean,
    val primaryContent: String?,
    val secondaryContent: String?,
    val primaryImg: String?,
    val secondaryImg: String?,
    val timestamp: Date?,
)

fun Chat.toChatDto(userId: String): ChatDto {
    return ChatDto(
        sentUserId = userId,
        primaryContent = primaryContent,
        secondaryContent = secondaryContent,
        primaryImg = primaryImg,
        secondaryImg = secondaryImg,
    )
}

