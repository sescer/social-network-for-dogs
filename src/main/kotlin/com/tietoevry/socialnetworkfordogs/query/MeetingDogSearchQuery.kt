package com.tietoevry.socialnetworkfordogs.query

import java.util.Date
/**
 * Class represents entity of searching query for searching meetings
 */
data class MeetingDogSearchQuery (
    val name: String?,
    val startDate: Date?,
    val endDate: Date?
)