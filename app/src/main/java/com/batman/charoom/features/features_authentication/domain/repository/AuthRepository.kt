package com.batman.charoom.features.features_authentication.domain.repository

interface AuthRepository {
    suspend fun register(email: String, password: String): Result<Unit>
     suspend fun login(email: String, password: String): Result<Unit>
    suspend fun signOut()
}