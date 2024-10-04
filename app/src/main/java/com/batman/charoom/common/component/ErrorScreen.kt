package com.batman.charoom.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChaRoomErrorScreen(
    title: String,
    message: String = "An unexpected error occurred. Please try again later.",
    onRetry: () -> Unit = {},
    icon: ImageVector = Icons.Filled.ErrorOutline,
    buttonText: String = "Retry"
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Error Icon",
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                modifier = Modifier
                    .size(56.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            TextButton(
                onClick = { onRetry() },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Text(
                    text = buttonText,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MinimalErrorScreenPreview() {
    ChaRoomErrorScreen(
        title = "Something Went Wrong",
        onRetry = { })
}
