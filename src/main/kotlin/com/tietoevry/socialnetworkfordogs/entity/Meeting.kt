package com.tietoevry.socialnetworkfordogs.entity

import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.*

@Entity
data class Meeting (
    //TODO id создателя встречич

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    val name : String = "",
    @CreationTimestamp
    val creationDate : Date? =  Date(0),
    val meetingDate : Date = Date(0),
    val author: Long = 0,
    @Enumerated
    val status : MeetingStatus = MeetingStatus.NONE
)