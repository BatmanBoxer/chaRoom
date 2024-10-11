package com.batman.charoom.features.feature_chat_screen.domain.repository

import com.batman.charoom.common.dataClass.UserData

interface SearchRepository {
    suspend fun searchUsers(name:String):List<UserData>
    suspend fun addUserToChat(id:String):Result<Unit>
}