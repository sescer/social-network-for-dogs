package com.tietoevry.socialnetworkfordogs.query

import java.time.Instant
/**
 * Class represents entity of searching query for searching messages of dog
 */
data class MessageSearchQuery (
    val content: String?,
    val startDate: Instant?,
    val endDate: Instant?
)