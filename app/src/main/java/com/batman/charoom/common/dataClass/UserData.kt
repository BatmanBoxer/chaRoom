package com.batman.charoom.common.dataClass

import androidx.compose.ui.graphics.painter.Painter

/**
 * Created by Pronay Sarker on 04/10/2024 (11:50 PM)
 */
data class UserData(
    val image: Painter? = null,
    val name: String
    /**
     * add more param if needed
     */
)