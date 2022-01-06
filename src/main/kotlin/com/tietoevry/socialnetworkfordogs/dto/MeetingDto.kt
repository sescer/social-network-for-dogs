package com.tietoevry.socialnetworkfordogs.dto

import com.tietoevry.socialnetworkfordogs.entity.MeetingStatus
import java.time.Instant

data class MeetingDto (
    val name: String,
    val meetingDate: Instant,
    val authorId: Long,
    val status: MeetingStatus
)