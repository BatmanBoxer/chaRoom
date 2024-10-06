package com.batman.charoom.features.feature_chat_screen.data.repository

import android.util.Log
import com.batman.charoom.features.feature_chat_screen.data.remote.dto.ChatDto
import com.batman.charoom.features.feature_chat_screen.domain.repository.ChatRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ChatRepository {
    override suspend fun getChats(chatId: String, onResult: (List<ChatDto>) -> Unit) {

        firestore.collection("chats")
            .addSnapshotListener { snapshots, exception ->
                if (exception != null) {
                    return@addSnapshotListener
                }
                if (snapshots != null) {
                    val chatList = mutableListOf<ChatDto>()

                    for (document in snapshots.documents) {
                        val chatDto = document.toObject(ChatDto::class.java)
                        chatDto?.let { chatList.add(it) }
                    }

                    Log.d("Firestore", "Converted ChatDto List: $chatList")
                }
            }
    }


    override suspend fun addChat(chatDto: ChatDto) {

    }

    override suspend fun loadMoreChat(pageNumber: Int): List<ChatDto> {
        return emptyList()
    }
}