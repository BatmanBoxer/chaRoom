package com.batman.charoom.features.features_authentication.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(icon:ImageVector,placeholder:String,onChange:(String)->Unit){
    var message by remember {
        mutableStateOf("")
    }
    TextField(
        value = message,
        onValueChange = {
            message = it
            onChange(message)
                        },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp)
            )
            .shadow(2.dp, RoundedCornerShape(15.dp))
        ,
        placeholder = { Text("Type a message...", color = MaterialTheme.colorScheme.inverseSurface) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                imageVector = icon,  // Replace with your desired icon
                contentDescription = "Send Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
    )

}