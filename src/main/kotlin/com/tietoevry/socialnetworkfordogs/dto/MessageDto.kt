package com.tietoevry.socialnetworkfordogs.dto

data class MessageDto (
    val dogFrom: Long,
    val dogTo: Long,
    val content: String
)