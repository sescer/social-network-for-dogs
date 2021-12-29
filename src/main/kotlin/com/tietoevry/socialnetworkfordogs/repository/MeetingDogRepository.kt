package com.tietoevry.socialnetworkfordogs.repository

import com.tietoevry.socialnetworkfordogs.entity.MeetingDog
import com.tietoevry.socialnetworkfordogs.entity.MeetingDogId
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

interface MeetingDogRepository : CrudRepository<MeetingDog, MeetingDogId>{

    @Query(value = "select m.meeting_id from meeting_dog m where m.dog_id = (:id)", nativeQuery = true)
    fun findDogMeetings(id: Long): List<Long>

    @Modifying
    @Transactional
    @Query(value="delete from meeting_dog m where m.meeting_id = (:id)", nativeQuery = true)
    fun deleteMeeting(id: Long)
}
