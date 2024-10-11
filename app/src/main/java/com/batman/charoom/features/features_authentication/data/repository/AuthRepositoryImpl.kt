package com.batman.charoom.features.features_authentication.data.repository

import com.batman.charoom.common.dataClass.ProfileDb
import com.batman.charoom.common.dataClass.UserData
import com.batman.charoom.features.feature_profile.domain.model.User
import com.batman.charoom.features.features_authentication.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestoreSettings
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    override suspend fun register(name: String,email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            addUserToDb(name,email,password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    private suspend fun addUserToDb(name:String,email: String,password: String){
         try {
            firestore.collection("users").add(
                ProfileDb(
                    name = name,
                    email = email,
                    profileImg = "no image for now",
                    userId = firebaseAuth.uid ?: "no user id found"
                )
            ).await()
        }catch (e:Exception){

        }
    }
}
