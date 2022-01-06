package com.tietoevry.socialnetworkfordogs.mapper

import com.tietoevry.socialnetworkfordogs.dto.MeetingDto
import com.tietoevry.socialnetworkfordogs.entity.Meeting
import java.time.Instant

fun MeetingDto.mapToEntity(): Meeting {
    return Meeting(
        0,
        name,
        Instant.MIN,
        meetingDate,
        authorId,
        status
    )
}

fun Meeting.mapToDto(): MeetingDto {
    return MeetingDto(
        name,
        meetingDate,
        authorId,
        status
    )
}