package com.tietoevry.socialnetworkfordogs.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.util.Date
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Класс встречи
 */
@Entity
data class Meeting(
    //TODO id создателя встречич

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,

    @CreationTimestamp
    val creationDate: LocalDate = LocalDate.now(),

    val meetingDate: Date,

    val author: Long,

    @Enumerated
    val status: MeetingStatus,
)