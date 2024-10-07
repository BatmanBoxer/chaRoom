package com.batman.charoom.features.feature_chat_screen.data.repository

import android.util.Log
import com.batman.charoom.features.feature_chat_screen.data.remote.dto.ChatDto
import com.batman.charoom.features.feature_chat_screen.domain.model.RoomInfoDto
import com.batman.charoom.features.feature_chat_screen.domain.repository.ChatRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ChatRepository {
    override suspend fun getChats(chatId: String, onResult: (List<ChatDto>) -> Unit) {
        firestore.collection("chats_rooms/jQWCJsECxehUAGrz7qa0/chats")
            .addSnapshotListener { snapshots, exception ->
                if (exception != null) {
                    return@addSnapshotListener
                }
                if (snapshots != null) {
                    val chatList = mutableListOf<ChatDto>()
                    for (document in snapshots.documents) {
                        Log.d("paras","unfiltered data ${document.data}")
                        val chatDto = document.toObject(ChatDto::class.java)
                        chatDto?.let { chatList.add(it) }
                    }
                    onResult(chatList)
                }
            }
    }


    override suspend fun addChat(roomId: String,chatDto: ChatDto) {
        try {
            val chatRef = firestore.collection("chats_rooms/$roomId/chats")
            chatRef.add(chatDto).await()
        } catch (exception: Exception) {
            Log.e("Firestore", "Error adding chat", exception)
        }
    }

    override suspend fun loadMoreChat(pageNumber: Int): List<ChatDto> {
        return emptyList()
    }

    override suspend fun getRoomInfo(roomId: String): RoomInfoDto? {
        return try {
            val snapshot = firestore.collection("chats_rooms")
                .document(roomId)
                .get()
                .await()
            if (snapshot.exists()) {
                val roomData = snapshot.data
                roomData?.let {
                    RoomInfoDto(it.toString())
                }
            } else {
                null
            }
        } catch (exception: Exception) {
            null
        }
    }

}
