package com.batman.charoom.features.features_authentication.presentation.screens.signup_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.features.features_authentication.domain.model.SignUpData
import com.batman.charoom.features.features_authentication.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Pronay Sarker on 04/10/2024 (9:19 PM)
 */


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _signupUiState = MutableStateFlow<SignupUiState>(SignupUiState.Initial)
    val signupUiState: StateFlow<SignupUiState> = _signupUiState

    fun signUp(signUpData: SignUpData) {

        viewModelScope.launch {
            _signupUiState.value = SignupUiState.ShowProgress
            val signUp = repository.register(signUpData.email, signUpData.password)
            signUp.onSuccess {
                _signupUiState.value = SignupUiState.Success
            }.onFailure {
            _signupUiState.value = SignupUiState.ShowErrorToast(it.localizedMessage ?: "Unknown Error Could`t Sign up")
            }
        }
    }



}
//@HiltViewModel
//class SignupViewmodel @Inject constructor(
//
//) : ViewModel() {
//    private val _signupUiState = MutableStateFlow<SignupUiState>(SignupUiState.Initial)
//    val signupUiState: StateFlow<SignupUiState> = _signupUiState
//
//    fun signup(signUpData: SignUpData) {
//        _signupUiState.value = SignupUiState.ShowProgress
//
//        viewModelScope.launch {
//            /**
//             * for testing purpose
//             */
//            delay(999)
//            _signupUiState.value = SignupUiState.Success
//        }
//    }
//}
