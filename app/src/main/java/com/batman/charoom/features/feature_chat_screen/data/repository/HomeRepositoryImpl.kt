package com.batman.charoom.features.feature_chat_screen.data.repository

import com.batman.charoom.common.dataClass.UserData
import com.batman.charoom.features.feature_chat_screen.data.remote.dto.ChatRoom
import com.batman.charoom.features.feature_chat_screen.domain.repository.HomeRepository
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : HomeRepository {
    override suspend fun addUserToChatRoom(
        user: String,
        chatRoomId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        try {
            firebaseFirestore
                .collection("chats_room")
                .document(chatRoomId)
                .set(
                    hashMapOf(
                        "participants" to FieldValue.arrayUnion(user)
                    ),
                    SetOptions.merge()
                )
                .await()
            onSuccess()
            println("User $user added to chat room $chatRoomId")
        } catch (e: Exception) {
            println("Error adding user to chat room: ${e.message}")
        }
    }
    override suspend fun listenForChatRoomsForParticipant(userId: String, onResult: (List<ChatRoom>) -> Unit) {
        val chatRoomsRef = firebaseFirestore.collection("chats_room")

        chatRoomsRef.whereArrayContains("participants", userId)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    println("Listen failed: ${e.message}")
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val chatRooms: List<ChatRoom> = snapshot.documents.mapNotNull { document ->
                        val chatRoomId = document.id
                        val chatRoom = document.toObject(ChatRoom::class.java)?.copy(chatRoomId = chatRoomId)
                        chatRoom
                    }

                    onResult(chatRooms)
                } else {
                    println("Current data: null")
                }
            }
    }

    override suspend fun getUserData(userId: String): UserData? {
        return try {
            val querySnapshot = firebaseFirestore.collection("users")
                .whereEqualTo("userId", userId)
                .get()
                .await()

            val document = querySnapshot.documents.firstOrNull()

            document?.let {
                UserData(
                    documentId = it.id,
                    imgUrl = it.getString("profileImg") ?: "",
                    name = it.getString("name") ?: ""
                )
            }
        } catch (e: Exception) {
            println("Error getting user data: ${e.message}")
            null
        }
    }


}