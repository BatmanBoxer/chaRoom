package com.batman.charoom.features.features_authentication.domain.model

/**
 * Created by Pronay Sarker on 04/10/2024 (9:21 PM)
 */
data class SignUpData(
    var name: String,
    var email: String,
    var password: String,
    var confirmPassword:String,
)
