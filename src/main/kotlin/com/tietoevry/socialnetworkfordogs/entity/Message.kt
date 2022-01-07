package com.tietoevry.socialnetworkfordogs.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Table
import javax.persistence.Id

/**
 * Class represents messages entity
 */
@Entity
@Table(name = "messages")
data class Message (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val dogFrom: Long = 0L,

    val dogTo: Long = 0L,

    val content: String = "",

    @CreationTimestamp
    val creationDate: Instant = Instant.MIN
)