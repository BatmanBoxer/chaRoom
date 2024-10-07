package com.batman.charoom.features.feature_chat_screen.presentation.screens.Chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.batman.charoom.features.feature_chat_screen.domain.model.Chat


@Composable
fun ChatTemplate(chat: Chat) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        Column(
            modifier = Modifier.align(if (chat.isUser) Alignment.TopEnd else Alignment.TopStart)
        ) {

            chat.secondaryContent?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray
                    ),
                    modifier = Modifier
                        .padding(bottom = 0.dp)
                        .background(
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                        .widthIn(max = 200.dp)
                )
            }


            chat.secondaryImg?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "secondary chat image",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .shadow(8.dp, RoundedCornerShape(16.dp), true)
                )
            }


            if (chat.primaryImg != null) {
                AsyncImage(
                    model = chat.primaryImg,
                    contentDescription = "chat image",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .shadow(8.dp, RoundedCornerShape(16.dp), true)
                )
            }


            chat.primaryContent?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (chat.isUser) Color.White else Color.Black
                    ),
                    modifier = Modifier
                        .background(
                            if (chat.isUser) Color.Blue else Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                        .widthIn(max = 200.dp)
                )
            }
        }
    }
}
