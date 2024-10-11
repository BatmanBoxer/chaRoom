package com.batman.charoom.features.feature_chat_screen.data.repository

import android.util.Log
import com.batman.charoom.features.feature_chat_screen.data.remote.dto.ChatDto
import com.batman.charoom.features.feature_chat_screen.domain.model.RoomInfoDto
import com.batman.charoom.features.feature_chat_screen.domain.repository.ChatRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import java.lang.Error
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ChatRepository {

    private var chatListenerRegistration: ListenerRegistration? = null
    override suspend fun getChats(chatId: String, limit:Long,onResult: (List<ChatDto>) -> Unit, onError: (String) -> Unit) {
        chatListenerRegistration?.remove()
        chatListenerRegistration = firestore.collection("chats_room/$chatId/chats")
            .orderBy("time", Query.Direction.DESCENDING)
            .limit(limit)
            .addSnapshotListener { snapshots, exception ->
                Log.d("paras","snapshot listener")
                if (exception != null) {
                    onError(exception.toString())
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
            val chatRef = firestore.collection("chats_room/$roomId/chats")
            chatRef.add(chatDto).await()
            Log.d("Firestore", "sucess adding chat")

        } catch (exception: Exception) {
            Log.e("Firestore", "Error adding chat", exception)
        }
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
