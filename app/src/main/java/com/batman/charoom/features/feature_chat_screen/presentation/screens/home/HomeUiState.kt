package com.batman.charoom.features.feature_chat_screen.presentation.screens.home

import com.batman.charoom.features.feature_chat_screen.domain.model.RecentChat

/**
 * Created by Pronay Sarker on 04/10/2024 (11:48 PM)
 */
sealed class HomeUiState {

    data object Initial : HomeUiState()

    data object Loading : HomeUiState()

    data class Success(val chats: List<RecentChat>) : HomeUiState()

    data class Error(val message: String) : HomeUiState()

}