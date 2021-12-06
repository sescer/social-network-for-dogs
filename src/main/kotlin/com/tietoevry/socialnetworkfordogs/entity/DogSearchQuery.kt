package com.tietoevry.socialnetworkfordogs.entity

import com.tietoevry.socialnetworkfordogs.entity.color.Color

data class DogSearchQuery (
    val nickname: String,
    val startAge: Int,
    val endAge: Int,
    val breed: Breed,
    val color: Color,
    val sex: Sex
)