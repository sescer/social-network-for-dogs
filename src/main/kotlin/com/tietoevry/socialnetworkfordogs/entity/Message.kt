package com.tietoevry.socialnetworkfordogs.entity

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "messages")
data class Message (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val dogFrom: Long = 0,
    val dogTo: Long = 0,
    val content: String = "",
    @CreationTimestamp
    val creationDate: Date  = Date(0-0-0)
)