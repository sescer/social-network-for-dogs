package com.tietoevry.socialnetworkfordogs.mapper

import com.tietoevry.socialnetworkfordogs.dto.MessageDto
import com.tietoevry.socialnetworkfordogs.entity.Message
import java.time.Instant

fun MessageDto.mapToEntity(): Message {
    return Message(
        0,
        dogFrom,
        dogTo,
        content,
        Instant.MIN,
    )
}

fun Message.mapToDto(): MessageDto {
    return MessageDto(
        dogFrom,
        dogTo,
        content
    )
}