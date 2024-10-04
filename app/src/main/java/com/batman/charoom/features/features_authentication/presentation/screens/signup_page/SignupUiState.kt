package com.batman.charoom.features.features_authentication.presentation.screens.signup_page

/**
 * Created by Pronay Sarker on 04/10/2024 (8:18 PM)
 */
sealed class SignupUiState {
    data object Initial : SignupUiState()

    data object ShowProgress : SignupUiState()

    data object Success : SignupUiState()

    data class ShowErrorToast(val error: String) : SignupUiState()

}