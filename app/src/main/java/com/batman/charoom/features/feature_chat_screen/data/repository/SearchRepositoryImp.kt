package com.batman.charoom.features.feature_chat_screen.data.repository

import android.util.Log
import com.batman.charoom.common.dataClass.UserData
import com.batman.charoom.features.feature_chat_screen.domain.repository.SearchRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
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

    override suspend fun addUserToChat(id: String): Result<Unit> {
        try {
            val localUser = firebaseAuth.currentUser?.uid
            Log.d("paras","id is $id and localid is $localUser")
            firestore.collection("chats_room").add(
                hashMapOf(
                    "participants" to FieldValue.arrayUnion(id,localUser),
                ),

            ).await()
            Log.d("paras","added")
            return Result.success(Unit)
        } catch (e:Exception){
            Log.d("paras koirala",e.message.toString())
          return Result.failure(e)
        }

    }

}