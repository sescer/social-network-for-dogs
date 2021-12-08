package com.tietoevry.socialnetworkfordogs.repository

import com.tietoevry.socialnetworkfordogs.entity.Meeting
import org.springframework.data.repository.CrudRepository

interface MeetingRepository : CrudRepository<Meeting, Long>