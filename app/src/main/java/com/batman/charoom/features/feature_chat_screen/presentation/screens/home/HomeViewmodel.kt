package com.batman.charoom.features.feature_chat_screen.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.features.feature_chat_screen.domain.model.RecentChat
import com.batman.charoom.features.feature_chat_screen.domain.repository.HomeRepository
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
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        viewModelScope.launch {
            homeRepository.addUserToChatRoom("darwin", "chatRoomId",
                onSuccess = {
                    Log.d("HomeViewmodel", "User added to chat room")
                },
                onFailure = {
                    Log.d("HomeViewmodel", "Error adding user to chat room: $it")
                }
            )
            homeRepository.listenForChatRoomsForParticipant("darwin") { chatRooms ->
                Log.d("HomeViewmodel", "Chat rooms: $chatRooms")
            }
            val user = homeRepository.getUserData("testid")

            Log.d("HomeViewmodel", "User:$user")
        }

        fetchData("darwin")
    }

    private fun fetchData(Localuserid: String) {
        val formater = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        viewModelScope.launch {
            _homeUiState.value = HomeUiState.Loading
            homeRepository.listenForChatRoomsForParticipant(Localuserid) { chatRooms ->
                val recentChat: MutableList<RecentChat> = mutableListOf()

                // Use coroutineScope to await results
                viewModelScope.launch {
                    // Create a list of deferred results using async
                    val deferredResults = chatRooms.map { room ->
                        async {
                            val userID = "testid"
                            // Call the suspend function within the async block
                            val userData = homeRepository.getUserData(userID)
                            userData?.let {
                                RecentChat(
                                    userId = userID,
                                    it.name ?: "null",
                                    it.imgUrl ?: "null",
                                    formater.format(room.lastMessageTime?.toDate() ?: Date()),
                                    timestamp = room.lastMessageTime.toString(),

                                )
                            }
                        }
                    }

                    // Await all results and filter out nulls
                    recentChat.addAll(deferredResults.awaitAll().filterNotNull())

                    _homeUiState.value = HomeUiState.Success(recentChat)
                    Log.d("userChat", recentChat.toString())
                }

            }
        }
    }


}

