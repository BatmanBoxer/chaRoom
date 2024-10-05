package com.batman.charoom.navigation

import kotlinx.serialization.Serializable

@Serializable
object NavLogInScreen

@Serializable
object NavSignUpScreen

@Serializable
object NavHomeScreen

@Serializable
object NavTest

@Serializable
data class NavChatScreen(
    val chatID:String = "batman"
)