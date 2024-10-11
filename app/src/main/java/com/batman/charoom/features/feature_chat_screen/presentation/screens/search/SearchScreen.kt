package com.batman.charoom.features.feature_chat_screen.presentation.screens.search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(
    navigateTOProfile:(id:String)->Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    LazyColumn {
        items(viewModel.searchUserList) {
            Log.d("paras","name ${it.name}")
            Text(it.name ?: "null", modifier = Modifier.clickable {
                if (it.documentId != null){
                    navigateTOProfile(it.documentId)
                }
            })
        }
    }

}