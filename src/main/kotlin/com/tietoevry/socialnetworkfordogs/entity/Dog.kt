package com.tietoevry.socialnetworkfordogs.entity

import com.tietoevry.socialnetworkfordogs.entity.color.Color
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
/**
 * Class represents entity dog
 */
@Entity
data class Dog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nickname: String = "",

    @Enumerated
    val breed: Breed = Breed.NONE,

    val age: Int = -1,

    @Enumerated
    val hairColor: Color = Color.NONE,

    @Enumerated
    val sex: Sex = Sex.NONE
)