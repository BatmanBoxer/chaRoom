package com.batman.charoom.features.feature_profile.presentation.screens.profile

import com.batman.charoom.common.dataClass.UserData
import com.batman.charoom.common.utils.Resource

/**
 * Created by Pronay Sarker on 07/10/2024 (7:38 PM)
 */
sealed class ProfileUiState {

    data object Loading : ProfileUiState()

    data class Success(val userData: UserData) : ProfileUiState()

    data class Error(val message: String) : ProfileUiState()

}

