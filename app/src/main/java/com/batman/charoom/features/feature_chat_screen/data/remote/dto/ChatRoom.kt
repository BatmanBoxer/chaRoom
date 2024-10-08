package com.batman.charoom.features.feature_chat_screen.data.remote.dto

import com.batman.charoom.features.feature_chat_screen.domain.model.RecentChat
import com.google.firebase.Timestamp

data class ChatRoom(
    val chatRoomId:String? = null,
    val title: String? = null,
    val lastMessageTime: Timestamp? = null,
    val lastMessage: String? = null,
    val participants: List<String>? = null,
)