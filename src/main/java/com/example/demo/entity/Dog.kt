package com.example.demo.entity

import lombok.NoArgsConstructor
import java.awt.Color
import javax.persistence.*

@Entity
@NoArgsConstructor
data class Dog(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val nickname: String,
        val breed: Breed,
        val age: Integer,
        val hairColor: Color
)