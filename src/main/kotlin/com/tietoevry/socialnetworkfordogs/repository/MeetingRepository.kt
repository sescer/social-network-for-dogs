package com.tietoevry.socialnetworkfordogs.repository

import com.tietoevry.socialnetworkfordogs.entity.Meeting
import com.tietoevry.socialnetworkfordogs.entity.MeetingDog
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MeetingRepository : CrudRepository<Meeting, Long>, JpaSpecificationExecutor<Meeting>