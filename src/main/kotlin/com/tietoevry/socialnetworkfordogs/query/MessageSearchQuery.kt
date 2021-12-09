package com.tietoevry.socialnetworkfordogs.query

import java.util.*

data class MessageSearchQuery (
    val content: String?,
    val startDate: Date?,
    val endDate: Date?
)