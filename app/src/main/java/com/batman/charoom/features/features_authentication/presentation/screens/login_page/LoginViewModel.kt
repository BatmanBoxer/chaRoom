package com.batman.charoom.features.features_authentication.presentation.screens.login_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Pronay Sarker on 04/10/2024 (8:02 PM)
 */
@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Initial)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun login(username: String, password: String) {
        _loginUiState.value = LoginUiState.ShowProgress

        viewModelScope.launch {
            /**
             * for testing purpose
             */
            delay(1000)
            _loginUiState.value = LoginUiState.Success
        }
    }
}