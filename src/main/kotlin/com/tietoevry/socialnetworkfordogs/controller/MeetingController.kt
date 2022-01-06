package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.entity.Meeting
import com.tietoevry.socialnetworkfordogs.entity.MeetingDog
import com.tietoevry.socialnetworkfordogs.query.MeetingDogSearchQuery
import com.tietoevry.socialnetworkfordogs.service.MeetingDogService
import com.tietoevry.socialnetworkfordogs.service.MeetingService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/meeting")
class MeetingController (
    private val service: MeetingService,
    private val meetingDogService: MeetingDogService

) {
    @PostMapping("/create")
    fun createMeeting(@RequestBody meeting: Meeting): Long? {
        val meetingId =  service.createMeeting(meeting)
        val newMeetingDog = MeetingDog(
            meetingId,
            meeting.author
        )
        meetingDogService.subscribeOnMeeting(newMeetingDog)
        return  meetingId
    }

    @GetMapping("/")
    fun getMeeting(@RequestBody id: Long): Meeting {
        return service.getMeeting(id)
    }

    @PostMapping("/update")
    fun updateMeeting(@RequestBody meeting: Meeting) {
        service.updateMeeting(meeting)
    }

    @DeleteMapping("/")
    fun deleteMeeting(@RequestBody id: Long) {
        meetingDogService.deleteMeeting(id)
        service.deleteMeeting(id)
    }

    @PostMapping("/search")
    fun search(@RequestBody meetingDogSearchQuery: MeetingDogSearchQuery): List<Meeting> {
        return service.searchMeetingDog(meetingDogSearchQuery)
    }

}