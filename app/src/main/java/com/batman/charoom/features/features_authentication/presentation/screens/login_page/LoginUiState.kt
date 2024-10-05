package com.batman.charoom.features.features_authentication.presentation.screens.login_page

/**
 * Created by Pronay Sarker on 04/10/2024 (8:03 PM)
 */
sealed class LoginUiState {

    data object Initial : LoginUiState()

    data object ShowProgress : LoginUiState()

    data class ShowValidationErrorString(val error: String) : LoginUiState()

    data object Success : LoginUiState()

}