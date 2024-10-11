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
object NavChatScreenRoute

@Serializable
object NavAuthRoute

@Serializable
data class NavChatScreen(
    val chatId:String,
)

@Serializable
object NavProfileRoute

@Serializable
data class NavProfileScreen(
    val id:String
)

@Serializable
data class NavSearchScreen (
    val name:String
)