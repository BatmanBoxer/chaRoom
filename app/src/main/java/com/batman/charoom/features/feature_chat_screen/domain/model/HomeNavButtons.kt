package com.batman.charoom.features.feature_chat_screen.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class HomeNavButtons(
    val name : String,
    val focusedIcon : ImageVector,
    val unfocusedIcon : ImageVector,
    val badgeCount : Int? = null
)

