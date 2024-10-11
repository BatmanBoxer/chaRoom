package com.batman.charoom.features.feature_profile.presentation.screens.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.common.dataClass.UserData
import com.batman.charoom.features.feature_chat_screen.domain.repository.HomeRepository
import com.batman.charoom.features.feature_chat_screen.domain.repository.SearchRepository
import com.batman.charoom.features.feature_profile.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Pronay Sarker on 07/10/2024 (7:36 PM)
 */
@HiltViewModel
class ProfileViewmodel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val homeRepository: HomeRepository,
    private val searchRepository: SearchRepository,
    private val auth: FirebaseAuth

) : ViewModel() {
    private val localUser = auth.currentUser?.uid
    private val user = savedStateHandle.get<String>("id")
    private val _profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val profileUiState = _profileUiState.asStateFlow()

    init {
        fetchUserData()
    }

    fun fetchUserData() {
        viewModelScope.launch {
            val userData: UserData? = homeRepository.getUserData(user ?: "")
            if (userData != null) {
                _profileUiState.value = ProfileUiState.Success(
                    User(
                        isLocalUser = userData.name == localUser,
                        name = userData.name,
                        email = userData.email,
                        imgUrl = userData.imgUrl
                    )

                )
            }
        }
    }
    fun addUserToChat(){
        viewModelScope.launch {
            if (user != null) {
                searchRepository.addUserToChat(id = user)
            }
        }
    }
}