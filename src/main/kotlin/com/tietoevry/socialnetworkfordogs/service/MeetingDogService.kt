package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.MeetingDog
import com.tietoevry.socialnetworkfordogs.entity.MeetingDogId
import com.tietoevry.socialnetworkfordogs.repository.MeetingDogRepository
import org.springframework.stereotype.Service

@Service
class MeetingDogService (
    private val repository: MeetingDogRepository
) {
    fun createMeetingDog(meetingDog: MeetingDog): MeetingDog? {
        return repository.save(meetingDog)
    }

    fun getMeetingDog(id: MeetingDogId): MeetingDog {
        return repository.findById(id).get()
    }

    fun deleteMeetingDog(id: MeetingDogId) {
        repository.delete(getMeetingDog(id))
    }

}