package com.batman.charoom.features.feature_chat_screen.di

import com.batman.charoom.features.feature_chat_screen.data.repository.ChatRepositoryImpl
import com.batman.charoom.features.feature_chat_screen.data.repository.HomeRepositoryImpl
import com.batman.charoom.features.feature_chat_screen.domain.repository.ChatRepository
import com.batman.charoom.features.feature_chat_screen.domain.repository.HomeRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {
    @Provides
    @Singleton
    fun providesChatRepository(firebaseFirestore: FirebaseFirestore):ChatRepository{
        return ChatRepositoryImpl(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun providesHomeRepository(firebaseFirestore: FirebaseFirestore): HomeRepository {
        return HomeRepositoryImpl(firebaseFirestore)
    }

}