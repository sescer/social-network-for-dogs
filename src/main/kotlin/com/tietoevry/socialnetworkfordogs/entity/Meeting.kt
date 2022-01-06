package com.tietoevry.socialnetworkfordogs.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import javax.persistence.*

/**
 * Class represents entity meeting
 */
@Entity
data class Meeting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String = "",

    @CreationTimestamp
    val creationDate: Instant = Instant.MIN,

    val meetingDate: Instant = Instant.MIN,

    val authorId: Long = 0L,

    @Enumerated
    val status: MeetingStatus = MeetingStatus.NONE,
)