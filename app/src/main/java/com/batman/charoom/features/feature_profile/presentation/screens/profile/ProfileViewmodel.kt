package com.batman.charoom.features.feature_profile.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.common.dataClass.UserData
import com.batman.charoom.features.feature_profile.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Pronay Sarker on 07/10/2024 (7:36 PM)
 */
@HiltViewModel
class ProfileViewmodel @Inject constructor(

) : ViewModel() {
    private val _profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val profileUiState = _profileUiState.asStateFlow()

    fun fetchUserData() {
        viewModelScope.launch {
            delay(3000)
            _profileUiState.value = ProfileUiState.Success(
                User(
                    isLocalUser = true,
                    name = "Pronay Sarker",
                    email = "pronaycoding@gmail.com",
                    imgUrl = null,
                )
            )
        }
    }
}