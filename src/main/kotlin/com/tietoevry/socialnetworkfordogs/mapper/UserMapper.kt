package com.tietoevry.socialnetworkfordogs.mapper

import com.tietoevry.socialnetworkfordogs.dto.UserDto
import com.tietoevry.socialnetworkfordogs.entity.User

fun UserDto.mapToEntity(): User {
    return User(
        0,
        login,
        password,
        firstname,
        lastname,
        mail,
        phoneNumber,
    )
}

fun User.mapToDto(): UserDto {
    return UserDto(
        login,
        password,
        firstname,
        lastname,
        mail,
        phoneNumber,
    )
}
