package com.batman.charoom.features.feature_chat_screen.presentation.screens.search

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batman.charoom.common.dataClass.UserData
import com.batman.charoom.features.feature_chat_screen.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val name = savedStateHandle.get<String>("name")
    var searchUserList = mutableStateListOf<UserData>()



    init {
        searchId(name ?: "null")
    }

    fun initialize() {

    }

    private fun searchId(name: String) {
        viewModelScope.launch {
            val user = repository.searchUsers(name)
            searchUserList.clear()
            searchUserList.addAll(user)
            Log.d("paras", searchUserList.toString())
        }
    }

}