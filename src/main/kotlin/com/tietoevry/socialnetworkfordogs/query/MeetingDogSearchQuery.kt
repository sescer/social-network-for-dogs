package com.tietoevry.socialnetworkfordogs.query

import java.time.Instant

/**
 * Class represents entity of searching query for searching meetings
 */
data class MeetingDogSearchQuery (
    val name: String?,
    val startDate: Instant?,
    val endDate: Instant?
)