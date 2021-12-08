package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.MeetingDog
import com.tietoevry.socialnetworkfordogs.entity.MeetingDogId
import com.tietoevry.socialnetworkfordogs.repository.MeetingDogRepository
import org.springframework.stereotype.Service

@Service
class MeetingDogService (
    private val repository: MeetingDogRepository
) {
    fun subscribeOnMeeting(meetingDog: MeetingDog): MeetingDog {
        return repository.save(meetingDog)
    }

    fun getMeetings(id: Long): List<Long> {
        return repository.findDogMeetings(id)
    }

    fun getMeetingDog(id: MeetingDogId): MeetingDog {
        return repository.findById(id).get()
    }

    fun unsubscribeFromMeeting(id: MeetingDogId) {
        repository.delete(getMeetingDog(id))
    }

    fun deleteMeeting(id: Long) {
        repository.deleteMeeting(id)
    }
}