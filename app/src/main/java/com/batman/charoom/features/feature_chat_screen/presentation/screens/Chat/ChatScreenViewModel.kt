package com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.features.feature_chat_screen.domain.model.Chat
import com.batman.charoom.features.feature_chat_screen.presentation.screens.home.HomeUiState
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
    private val savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _chatUiState = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val chatUiState: StateFlow<ChatUiState> = _chatUiState

  //  val chatId: String? = savedStateHandle["chatId"]

    init {
        fetchChat()
    }
    private fun fetchChat(){
        _chatUiState.value = ChatUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            withContext(Dispatchers.Main){
                _chatUiState.value = ChatUiState.Success("none",sampleChats)
            }
        }
    }

}
val sampleChats = listOf(
    Chat(isUser = false, primaryContent = "HELLO THERE", secondaryContent = "HELLO BACK", img = null),
    Chat(isUser = true, primaryContent = "How are you?", secondaryContent = "I'm good, thanks!", img = null),
    Chat(isUser = false, primaryContent = "What are you up to?", secondaryContent = "Just working on some stuff.", img = null),
    Chat(isUser = true, primaryContent = "Want to grab lunch?", secondaryContent = "Sure, sounds good!", img = null),
    Chat(isUser = false, primaryContent = "Have you seen the new movie?", secondaryContent = "Not yet, but I want to!", img = null),
    Chat(isUser = true, primaryContent = "It's really good!", secondaryContent = "I heard it's a must-watch!", img = null),
    Chat(isUser = false, primaryContent = "Are you going to the party tonight?", secondaryContent = "Yes, I wouldn't miss it!", img = null),
    Chat(isUser = true, primaryContent = "What time should we meet?", secondaryContent = "Let's meet at 8 PM.", img = null),
    Chat(isUser = false, primaryContent = "Do you have any plans for the weekend?", secondaryContent = "Just some relaxation time.", img = null),
    Chat(isUser = true, primaryContent = "Sounds nice! Let's hang out.", secondaryContent = "Definitely!", img = null),
    Chat(isUser = false, primaryContent = "What's your favorite food?", secondaryContent = "I love sushi!", img = null),
    Chat(isUser = true, primaryContent = "I'm a pizza fan!", secondaryContent = "Pizza is great too!", img = null),
    Chat(isUser = false, primaryContent = "Have you read any good books lately?", secondaryContent = "Yes, I just finished one!", img = null),
    Chat(isUser = true, primaryContent = "I need some book recommendations.", secondaryContent = "I can help with that!", img = null),
    Chat(isUser = false, primaryContent = "Do you play any games?", secondaryContent = "Yes, I'm into RPGs!", img = null),
    Chat(isUser = true, primaryContent = "I love RPGs too!", secondaryContent = "They're so immersive!", img = null),
    Chat(isUser = false, primaryContent = "Have you traveled anywhere recently?", secondaryContent = "I went to the beach!", img = null),
    Chat(isUser = true, primaryContent = "Sounds fun! Which beach?", secondaryContent = "It was a local one!", img = null),
    Chat(isUser = false, primaryContent = "What's your favorite season?", secondaryContent = "I love autumn!", img = null),
    Chat(isUser = true, primaryContent = "Autumn is beautiful with the colors!", secondaryContent = "Absolutely!", img = null),
    Chat(isUser = false, primaryContent = "Are you watching any series right now?", secondaryContent = "Yes, I am!", img = null)
)
