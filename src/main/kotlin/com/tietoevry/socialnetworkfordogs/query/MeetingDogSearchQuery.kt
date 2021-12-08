package com.tietoevry.socialnetworkfordogs.query

import java.util.*

data class MeetingDogSearchQuery (
    val name: String?,
    val startDate: Date?,
    val endDate: Date?
)