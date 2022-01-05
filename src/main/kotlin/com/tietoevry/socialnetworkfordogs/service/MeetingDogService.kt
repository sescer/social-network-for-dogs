package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.MeetingDog
import com.tietoevry.socialnetworkfordogs.entity.MeetingDogId
import com.tietoevry.socialnetworkfordogs.repository.MeetingDogRepository
import org.springframework.stereotype.Service
/**
 * Class represents subscribing/unsubscribing on/from dog's meeting for user's
 */
@Service
class MeetingDogService (
    private val repository: MeetingDogRepository
) {

    /**
     * Method for subscribing on meeting
     *
     * @param meetingDog - meetingDog to subscribe on
     * @return - meetingDog
     */
    fun subscribeOnMeeting(meetingDog: MeetingDog): MeetingDog {
        return repository.save(meetingDog)
    }
    /**
     * Method for getting meetings by dog's id
     *
     * @param id - dog's id
     * @return - list of dog's meetings
     */
    fun getMeetings(id: Long): List<Long> {
        return repository.findDogMeetings(id)
    }
    /**
     * Method for getting meeting by meeting id
     *
     * @param id - meetingDog id to get
     * @return - meetingDog
     */
    fun getMeetingDog(id: MeetingDogId): MeetingDog {
        return repository.findById(id).get()
    }
    /**
     * Method for unsubscribing from meeting by meeting id
     *
     * @param id - meetingDog id to unsubscribe from
     */
    fun unsubscribeFromMeeting(id: MeetingDogId) {
        repository.delete(getMeetingDog(id))
    }
    /**
     * Method for deleting meeting by meeting id
     *
     * @param id - meetingDog id to delete
     */
    fun deleteMeeting(id: Long) {
        repository.deleteMeeting(id)
    }
}