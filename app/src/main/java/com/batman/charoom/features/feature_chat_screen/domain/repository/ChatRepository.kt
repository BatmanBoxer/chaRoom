package com.batman.charoom.features.feature_chat_screen.domain.repository

import com.batman.charoom.features.feature_chat_screen.data.remote.dto.ChatDto

interface ChatRepository {
    suspend fun getChats(chatId:String,onResult: (List<ChatDto>) -> Unit)
    suspend fun addChat(chatDto: ChatDto)
    suspend fun loadMoreChat(pageNumber:Int):List<ChatDto>
}