package com.batman.charoom.features.feature_chat_screen.domain.repository

import com.batman.charoom.common.dataClass.UserData
import com.batman.charoom.features.feature_chat_screen.data.remote.dto.ChatRoom

interface HomeRepository {
    suspend fun addUserToChatRoom(user:String, chatRoomId:String,onSuccess:()->Unit,onFailure:(String)->Unit)
    suspend fun listenForChatRoomsForParticipant(userId: String, onResult: (List<ChatRoom>) -> Unit)
    suspend fun getUserData(userId: String):UserData?
}