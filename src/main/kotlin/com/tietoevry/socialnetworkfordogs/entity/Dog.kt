package com.tietoevry.socialnetworkfordogs.entity

import com.tietoevry.socialnetworkfordogs.entity.color.Color
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Dog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val nickname: String,
    @Enumerated
    val breed: Breed,
    val age: Int,
    @Enumerated
    val hairColor: Color,
)