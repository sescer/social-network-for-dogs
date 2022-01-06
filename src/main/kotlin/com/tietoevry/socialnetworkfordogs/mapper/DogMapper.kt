package com.tietoevry.socialnetworkfordogs.mapper

import com.tietoevry.socialnetworkfordogs.dto.DogDto
import com.tietoevry.socialnetworkfordogs.entity.Dog

fun DogDto.mapToEntity(): Dog {
    return Dog(
        0,
        nickname,
        breed,
        age,
        hairColor,
        sex
    )
}

fun Dog.mapToDto(): DogDto {
    return DogDto(
        nickname,
        breed,
        age,
        hairColor,
        sex
    )
}
