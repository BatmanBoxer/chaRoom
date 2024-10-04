package com.batman.charoom.features.features_authentication.presentation.screens.login_page

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenTopAppBar(){
    TopAppBar(
        title = { Text("Log In") }
    )
}