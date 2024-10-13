package com.batman.charoom.features.feature_chat_screen.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.features.feature_chat_screen.domain.model.RecentChat
import com.batman.charoom.features.feature_chat_screen.domain.repository.HomeRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Pronay Sarker on 04/10/2024 (11:45 PM)
 */
@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val homeRepository: HomeRepository, private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState
    private val user = firebaseAuth.currentUser?.uid ?: "null"

    init {
        fetchData(user)
    }

    private fun fetchData(localUserId: String) {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        viewModelScope.launch {
            _homeUiState.value = HomeUiState.Loading

            homeRepository.listenForChatRoomsForParticipant(localUserId) { chatRooms ->
                val recentChats: MutableList<RecentChat> = mutableListOf()
                viewModelScope.launch {
                    chatRooms.map { room ->
                        val otherParticipantId =
                            room.participants?.firstOrNull { it != localUserId }
                        otherParticipantId?.let {
                            val userData = homeRepository.getUserData(it)
                            val recentChat = userData?.let { user ->
                                RecentChat(
                                    chatId = room.chatRoomId ?: "null",
                                    name = user.name ?: "Unknown",
                                    profileImageUrl = user.imgUrl ?: "default_image_url",
                                    lastMessage = formatter.format(
                                        room.lastMessageTime?.toDate() ?: Date()
                                    ),
                                    timestamp = room.lastMessageTime?.toString() ?: "Unknown"
                                )
                            }
                            if (recentChat != null) {
                                recentChats.add(recentChat)
                            }
                        }

                    }
                    _homeUiState.value = HomeUiState.Success(recentChats)
                }
            }
        }
    }


}

