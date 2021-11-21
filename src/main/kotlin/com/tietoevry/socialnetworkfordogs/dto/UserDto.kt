package com.tietoevry.socialnetworkfordogs.dto

data class UserDto(
    val login: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val mail: String,
    val phoneNumber: String,
)
