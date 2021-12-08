package com.tietoevry.socialnetworkfordogs.entity

import java.util.*
import javax.persistence.*

@Entity
data class Meeting (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    val name : String,
    val creationDate : Date?,
    val meetingDate : Date,
    @Enumerated
    val status : MeetingStatus
)