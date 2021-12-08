package com.tietoevry.socialnetworkfordogs.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(MeetingDogId::class)
data class MeetingDog (
    @Id
    val meetingId : Long,
    @Id
    val dogId : Long
)