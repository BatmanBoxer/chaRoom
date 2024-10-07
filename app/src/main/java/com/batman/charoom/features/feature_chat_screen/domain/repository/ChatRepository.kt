package com.batman.charoom.features.feature_chat_screen.domain.repository

import com.batman.charoom.features.feature_chat_screen.data.remote.dto.ChatDto
import com.batman.charoom.features.feature_chat_screen.domain.model.RoomInfoDto

interface ChatRepository {
    suspend fun getChats(chatId: String, onResult: (List<ChatDto>) -> Unit, onError: (String) -> Unit)
    suspend fun addChat(roomId: String,chatDto: ChatDto)
    suspend fun loadMoreChat(pageNumber:Int):List<ChatDto>
    suspend fun getRoomInfo(roomId:String): RoomInfoDto?
}