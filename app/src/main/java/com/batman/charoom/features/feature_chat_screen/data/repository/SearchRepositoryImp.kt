package com.batman.charoom.features.feature_chat_screen.data.repository

import com.batman.charoom.common.dataClass.UserData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore
) : SearchRepository {
    override suspend fun searchUsers(name: String): List<UserData> {
        return try {
            val querySnapshot = firestore.collection("users")
                .whereEqualTo("name", name)
                .get()
                .await()
            querySnapshot.documents.mapNotNull {
                UserData(
                    imgUrl = it.getString("img"),
                    name = it.getString("name"),
                    documentId = it.getString("userId")
                )
            }
        } catch (e: Exception) {
            println("Error getting user data: ${e.message}")
            emptyList()
        }
    }

}