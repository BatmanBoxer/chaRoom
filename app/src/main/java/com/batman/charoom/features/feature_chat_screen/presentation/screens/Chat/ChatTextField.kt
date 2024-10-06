package com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTextField(
    modifier: Modifier,
    onSend: (String) -> Unit,
    onClickPhoto: () -> Unit
) {
    var message by remember { mutableStateOf("") }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f),
            placeholder = { Text("Type your message...") },
            maxLines = 3,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
        )

        if (message == "") {
            IconButton(
                onClick = {
                    onClickPhoto()
                },
                modifier = Modifier
                    .size(48.dp)
                    .offset((-5).dp)
                    .background(Color(0xFFCC5252), shape = CircleShape)

            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = "Send Message",
                    tint = Color.White
                )
            }
        } else {
            IconButton(
                onClick = {
                    if (message.isNotBlank()) {
                        onSend(message)
                        message = ""
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .offset((-5).dp)
                    .background(Color(0xFFCC5252), shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send Message",
                    tint = Color.White
                )
            }

        }
    }


}

