package com.batman.charoom.features.features_authentication.presentation.screens.login_page

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.batman.charoom.common.utils.Resource
import com.batman.charoom.features.features_authentication.domain.repository.AuthRepository
import com.batman.charoom.features.features_authentication.domain.usecase.UseCaseLogIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Pronay Sarker on 04/10/2024 (8:02 PM)
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCaseLogIn: UseCaseLogIn
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Initial)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun login(email: String, password: String) {
        _loginUiState.value = LoginUiState.Success
//        Log.d("batman","login")
//        useCaseLogIn(email, password).onEach { result ->
//            when (result) {
//                is Resource.Error -> {
//                    _loginUiState.value =
//                        LoginUiState.ShowValidationErrorString(result.message ?: "Unknown error")
//                }
//
//                is Resource.Loading -> {
//                    _loginUiState.value = LoginUiState.ShowProgress
//                }
//
//                is Resource.Success -> {
//                    _loginUiState.value = LoginUiState.Success
//                }
//            }
//
//        }.launchIn(viewModelScope)

    }


}