package com.tietoevry.socialnetworkfordogs.query

import java.util.Date
/**
 * Class represents entity of searching query for searching messages of dog
 */
data class MessageSearchQuery (
    val content: String?,
    val startDate: Date?,
    val endDate: Date?
)