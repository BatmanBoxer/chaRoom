package com.batman.charoom.features.home

import com.batman.charoom.common.dataClass.RecentChat

/**
 * Created by Pronay Sarker on 04/10/2024 (11:48 PM)
 */
sealed class HomeUiState {

    data object Initial : HomeUiState()

    data object Loading : HomeUiState()

    data class Success(val chats: List<RecentChat>) : HomeUiState()

    data class Error(val message: String) : HomeUiState()

}