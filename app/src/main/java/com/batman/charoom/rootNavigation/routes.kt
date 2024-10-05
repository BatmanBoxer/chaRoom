package com.batman.charoom.rootNavigation

import kotlinx.serialization.Serializable

@Serializable
object NavLogInScreen

@Serializable
object NavSignUpScreen

@Serializable
object NavHomeScreen

@Serializable
object HomeRoute

@Serializable
object NavTest

@Serializable
object NavChatScreenRoure

@Serializable
object AuthNavRoute

@Serializable
data class NavChatScreen(
    val chatID:String = "batman"
)