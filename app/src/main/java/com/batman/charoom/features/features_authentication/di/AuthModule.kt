package com.batman.charoom.features.features_authentication.di

import com.batman.charoom.features.features_authentication.data.repository.AuthRepositoryImpl
import com.batman.charoom.features.features_authentication.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesAuthRepository(firebaseAuth: FirebaseAuth,firebaseFirestore: FirebaseFirestore):AuthRepository{
        return AuthRepositoryImpl(firebaseAuth,firebaseFirestore)
    }
}