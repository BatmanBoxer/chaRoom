package com.batman.charoom.features.feature_chat_screen.domain.model

data class Chat(
    val isUser:Boolean,
    val primaryContent:String?,
    val secondaryContent:String?,
    val img:String?,
    val secondaryImg:String?
)
