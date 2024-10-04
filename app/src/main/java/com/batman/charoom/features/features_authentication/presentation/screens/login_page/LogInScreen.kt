package com.batman.charoom.features.features_authentication.presentation.screens.login_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.batman.charoom.features.features_authentication.presentation.screens.components.InputField

@Composable
fun LoginScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        InputField(icon = Icons.Default.Email, placeholder = "Type a message..."){
        }
    }
}