package com.tietoevry.socialnetworkfordogs.query

import com.tietoevry.socialnetworkfordogs.entity.Breed
import com.tietoevry.socialnetworkfordogs.entity.Sex
import com.tietoevry.socialnetworkfordogs.entity.color.Color

data class DogSearchQuery (
    val nickname: String?,
    val startAge: Int?,
    val endAge: Int?,
    val breed: Breed?,
    val hairColor: Color?,
    val sex: Sex?
)