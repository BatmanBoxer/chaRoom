package com.batman.charoom.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.R
import com.batman.charoom.common.dataClass.RecentChat
import com.batman.charoom.common.dataClass.UserData
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
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

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Success(sampleChats))
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    fun fetchData() {
        _homeUiState.value = HomeUiState.Loading

        viewModelScope.launch {
            _homeUiState.value = HomeUiState.Success(sampleChats)
            delay(10000)
            _homeUiState.value = HomeUiState.Error("This is a test failure check")
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
