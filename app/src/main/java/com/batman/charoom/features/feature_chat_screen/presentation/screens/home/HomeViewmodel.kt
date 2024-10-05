package com.batman.charoom.features.feature_chat_screen.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.features.feature_chat_screen.domain.model.RecentChat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Pronay Sarker on 04/10/2024 (11:45 PM)
 */
@HiltViewModel
class HomeViewmodel @Inject constructor(

) : ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        Log.d("fetchdata","fetching")
        fetchData()
    }
    fun fetchData() {

        viewModelScope.launch {
            _homeUiState.value = HomeUiState.Loading
            delay(1000)
            _homeUiState.value = HomeUiState.Success(sampleChats)

        }
    }
}

val sampleChats = listOf(
    RecentChat("John Doe", "https://unsplash.it/200/200", "Hey, how's it going?", "12:45 PM"),
    RecentChat("Jane Smith", "https://via.placeholder.com/40", "See you tomorrow!", "11:30 AM"),
    RecentChat("Alex Johnson", "https://via.placeholder.com/40", "Thanks for the help!", "9:15 AM"),
    RecentChat("Emily Davis", "https://via.placeholder.com/40", "Can you call me?", "Yesterday"),
    RecentChat("John Doe", "https://unsplash.it/200/200", "Hey, how's it going?", "12:45 PM"),
    RecentChat("Jane Smith", "https://via.placeholder.com/40", "See you tomorrow!", "11:30 AM"),
    RecentChat("Alex Johnson", "https://via.placeholder.com/40", "Thanks for the help!", "9:15 AM"),
    RecentChat("Emily Davis", "https://via.placeholder.com/40", "Can you call me?", "Yesterday"),
    RecentChat("John Doe", "https://unsplash.it/200/200", "Hey, how's it going?", "12:45 PM"),
    RecentChat("Jane Smith", "https://via.placeholder.com/40", "See you tomorrow!", "11:30 AM"),
    RecentChat("Alex Johnson", "https://via.placeholder.com/40", "Thanks for the help!", "9:15 AM"),
    RecentChat("Emily Davis", "https://via.placeholder.com/40", "Can you call me?", "Yesterday"),
    RecentChat("Michael Brown", "https://via.placeholder.com/40", "Let's meet up later.", "2 days ago")
)
