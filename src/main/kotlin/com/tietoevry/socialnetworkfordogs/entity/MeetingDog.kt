package com.tietoevry.socialnetworkfordogs.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

@Entity
@IdClass(MeetingDogId::class)
@Table(name = "meeting_dog")
data class MeetingDog (
    @Id
    val meetingId : Long? = 0,
    @Id
    val dogId : Long = 0
)