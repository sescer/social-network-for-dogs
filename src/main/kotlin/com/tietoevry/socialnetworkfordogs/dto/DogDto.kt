package com.tietoevry.socialnetworkfordogs.dto

import com.tietoevry.socialnetworkfordogs.entity.Breed
import com.tietoevry.socialnetworkfordogs.entity.Sex
import com.tietoevry.socialnetworkfordogs.entity.color.Color

data class DogDto (
    val nickname: String,
    val breed: Breed,
    val age: Int,
    val hairColor: Color,
    val sex: Sex
)