package com.batman.charoom.features.feature_chat_screen.data.remote.dto

import com.batman.charoom.features.feature_chat_screen.domain.model.Chat

data class ChatDto(
    val sentUserId: String = "",
    val primaryContent: String? = null,
    val secondaryContent: String? = null,
    val primaryImg: String? = null,
    val secondaryImg: String? = null,
)

fun ChatDto.toChat(): Chat {
    return Chat(
        isUser = true,
        primaryContent =primaryContent,
        secondaryContent = secondaryContent,
        primaryImg = primaryImg,
        secondaryImg = secondaryImg
    )
}
