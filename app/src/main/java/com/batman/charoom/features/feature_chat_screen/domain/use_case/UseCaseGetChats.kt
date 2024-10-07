package com.batman.charoom.features.feature_chat_screen.domain.use_case

import com.batman.charoom.common.utils.Resource
import com.batman.charoom.features.feature_chat_screen.data.remote.dto.toChat
import com.batman.charoom.features.feature_chat_screen.domain.model.Chat
import com.batman.charoom.features.feature_chat_screen.domain.repository.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseCaseGetChats @Inject constructor(
    private val repository: ChatRepository,
    private val firebaseAuth: FirebaseAuth
) {
    suspend operator fun invoke(
        roomId: String,
        onSuccess: (List<Chat>) -> Unit,
        onError: (error: String) -> Unit
    ) {
        val userUid = firebaseAuth.uid
        repository.getChats(roomId,
            onResult = { chat ->
                onSuccess(chat.map { it.toChat(userUid ?: "null") })
            },
            onError = { error ->
                onError(error)
            }
        )
    }
}