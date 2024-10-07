package com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.features.feature_chat_screen.data.remote.dto.ChatDto
import com.batman.charoom.features.feature_chat_screen.data.remote.dto.toChat
import com.batman.charoom.features.feature_chat_screen.domain.model.Chat
import com.batman.charoom.features.feature_chat_screen.domain.repository.ChatRepository
import com.batman.charoom.features.feature_chat_screen.domain.use_case.UseCaseAddChats
import com.batman.charoom.features.feature_chat_screen.domain.use_case.UseCaseGetChats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCaseGetChats: UseCaseGetChats,
    private val useCaseAddChats: UseCaseAddChats,
) : ViewModel() {
    private val _chatUiState = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val chatUiState: StateFlow<ChatUiState> = _chatUiState
    private var currentLimit = MutableStateFlow(50L)

    private val roomId: String = savedStateHandle["chatId"] ?: ""

    init {
        fetchChat()
    }

    private fun fetchChat() {
        _chatUiState.value = ChatUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            currentLimit.collect { limit ->
                useCaseGetChats(roomId,
                    limit = limit,
                    onSuccess = {
                        _chatUiState.value = ChatUiState.Success("batman", it)
                    }, onError = {
                        _chatUiState.value = ChatUiState.Error(e = it)
                    })
            }

        }
    }
    fun changeLimit(){
        currentLimit.value += 100
    }

    fun addChat(chat: Chat) {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseAddChats(roomId, chat)
        }
    }



}
