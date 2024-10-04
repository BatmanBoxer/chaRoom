package com.batman.charoom.features.features_authentication.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.features.features_authentication.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
):ViewModel() {

    fun signUp(email:String,password: String){
        viewModelScope.launch {
            val result = repository.register(email,password)
            result.onSuccess {

            }.onFailure {

            }
        }
    }
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(email, password)
            result.onSuccess {

            }.onFailure {

            }
        }
    }
    fun logOut() {
        viewModelScope.launch {
          repository.signOut()
        }
    }

}