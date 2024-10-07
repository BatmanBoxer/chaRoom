package com.batman.charoom.features.feature_chat_screen.domain.use_case

import com.batman.charoom.features.feature_chat_screen.domain.model.Chat
import com.batman.charoom.features.feature_chat_screen.domain.model.toChatDto
import com.batman.charoom.features.feature_chat_screen.domain.repository.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

// created by batman cause batman bored and batman need to do stuff so batman no get bored

class UseCaseAddChats @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val repository: ChatRepository
) {
    private val userId = firebaseAuth.currentUser?.uid
    suspend operator fun invoke(chatID:String, chat:Chat){
        repository.addChat(chatID,chat.toChatDto(userId ?: "null"))
    }
}