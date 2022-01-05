package com.tietoevry.socialnetworkfordogs.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
/**
 * Class represents entity meeting
 */
@Entity
data class Meeting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val name: String,

    @CreationTimestamp
    val creationDate: Date = Date(0),

    val meetingDate: Date,

    val author: Long,

    @Enumerated
    val status: MeetingStatus,
)