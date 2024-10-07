package com.batman.charoom.features.feature_chat_screen.data.remote.dto

import com.batman.charoom.features.feature_chat_screen.domain.model.Chat
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class ChatDto(
    val sentUserId: String = "",
    val primaryContent: String? = null,
    val secondaryContent: String? = null,
    val primaryImg: String? = null,
    val secondaryImg: String? = null,
    @ServerTimestamp val time:Timestamp? = null,
)

fun ChatDto.toChat(userID:String): Chat {
    return Chat(
        isUser = sentUserId == userID,
        primaryContent =primaryContent,
        secondaryContent = secondaryContent,
        primaryImg = primaryImg,
        secondaryImg = secondaryImg,
        timestamp = time?.toDate()
    )
}
