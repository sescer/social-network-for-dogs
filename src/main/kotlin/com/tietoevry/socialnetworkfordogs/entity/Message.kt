package com.tietoevry.socialnetworkfordogs.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "messages")
data class Message (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val dogFrom: Long,
    val dogTo: Long,
    val content: String,
    @CreationTimestamp
    val creationDate: LocalDate,
)