package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Meeting
import com.tietoevry.socialnetworkfordogs.repository.MeetingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MeetingService (
    private val repository: MeetingRepository
) {
    fun createMeeting(meeting : Meeting) : Long?{
        val newMeeting = Meeting(
            meeting.id,
            meeting.name,
            Date(System.currentTimeMillis()),
            meeting.meetingDate,
            meeting.status
        )
        return repository.save(newMeeting).id
    }

    fun getMeeting(id : Long) : Meeting{
        return repository.findById(id).get()
    }

    //TODO: make it that `meeting.creationDate` cannot be changed in `updateMeeting`
    fun updateMeeting(meeting: Meeting) {
        repository.save(meeting)
    }

    fun deleteMeeting(id : Long) {
        repository.delete(getMeeting(id))
    }

}