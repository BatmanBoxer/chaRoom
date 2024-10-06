package com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.features.feature_chat_screen.domain.model.Chat
import com.batman.charoom.features.feature_chat_screen.domain.repository.ChatRepository
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
    private val repository: ChatRepository
):ViewModel() {
    private val _chatUiState = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val chatUiState: StateFlow<ChatUiState> = _chatUiState

     private val chatId: String? = savedStateHandle["chatID"]

    init {
       viewModelScope.launch {
           repository.getChats("chats"){}
       }
        fetchChat()
    }
    private fun fetchChat(){
        _chatUiState.value = ChatUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            withContext(Dispatchers.Main){
                _chatUiState.value = ChatUiState.Success(chatId.toString(),sampleChats)
            }
        }
    }
    private fun addChat(chatRoomId:String,chat: Chat){

    }

}









private val sampleChats = listOf(
    Chat(isUser = false, primaryContent = null, secondaryContent = "HELLO BACK", primaryImg = null, secondaryImg = null),
    Chat(isUser = true, primaryContent = "What are you up to?", secondaryContent = null, primaryImg = null, secondaryImg = null),
    Chat(isUser = false, primaryContent = null, secondaryContent = null, primaryImg = "https://unsplash.it/200/200", secondaryImg = null),
    Chat(isUser = true, primaryContent = null, secondaryContent = null, primaryImg = null, secondaryImg = "https://unsplash.it/200/201"),
    Chat(isUser = false, primaryContent = "Just working on some stuff.", secondaryContent = null, primaryImg = null, secondaryImg = null),
    Chat(isUser = true, primaryContent = null, secondaryContent = null, primaryImg = "https://unsplash.it/200/202", secondaryImg = null),
    Chat(isUser = false, primaryContent = "Have you seen the new movie?", secondaryContent = null, primaryImg = null, secondaryImg = "https://unsplash.it/200/203"),
    Chat(isUser = true, primaryContent = "It's really good!", secondaryContent = "I heard it's a must-watch!", primaryImg = null, secondaryImg = null),
    Chat(isUser = false, primaryContent = null, secondaryContent = null, primaryImg = "https://unsplash.it/200/204", secondaryImg = null),
    Chat(isUser = true, primaryContent = "What time should we meet?", secondaryContent = null, primaryImg = null, secondaryImg = "https://unsplash.it/200/205"),
    Chat(isUser = false, primaryContent = "What's your favorite food?", secondaryContent = null, primaryImg = null, secondaryImg = null),
    Chat(isUser = true, primaryContent = null, secondaryContent = null, primaryImg = "https://unsplash.it/200/206", secondaryImg = null),
    Chat(isUser = false, primaryContent = null, secondaryContent = null, primaryImg = "https://unsplash.it/200/207", secondaryImg = null),
    Chat(isUser = true, primaryContent = "I love RPGs too!", secondaryContent = null, primaryImg = null, secondaryImg = null),
    Chat(isUser = false, primaryContent = "Have you read any good books lately?", secondaryContent = null, primaryImg = null, secondaryImg = null),
    Chat(isUser = true, primaryContent = "Sounds fun! Which beach?", secondaryContent = null, primaryImg = null, secondaryImg = "https://unsplash.it/200/208"),
    Chat(isUser = false, primaryContent = null, secondaryContent = null, primaryImg = null, secondaryImg = "https://unsplash.it/200/209"),
    Chat(isUser = true, primaryContent = null, secondaryContent = null, primaryImg = "https://unsplash.it/200/210", secondaryImg = null),
    Chat(isUser = false, primaryContent = "What's your favorite season?", secondaryContent = null, primaryImg = null, secondaryImg = null),
    Chat(isUser = false, primaryContent = null, secondaryContent = null, primaryImg = "https://unsplash.it/200/212", secondaryImg = null)
)
