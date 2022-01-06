package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.entity.MeetingDog
import com.tietoevry.socialnetworkfordogs.entity.MeetingDogId
import com.tietoevry.socialnetworkfordogs.service.MeetingDogService
import org.springframework.web.bind.annotation.*

//TODO
@RestController
@RequestMapping("api/v1/meeting/dog")
class MeetingDogController (
    private val service: MeetingDogService
){
    @PostMapping("/create")
    fun createMeeting(@RequestBody meetingDog: MeetingDog): MeetingDog {
        return service.subscribeOnMeeting(meetingDog)
    }

    @GetMapping("/{id}/meetings")
    fun getMeetingDog(@PathVariable id: Long): List<Long> {
        return service.getMeetings(id)
    }

    @DeleteMapping("/")
    fun deleteMeeting(@RequestBody id: MeetingDogId) {
        service.unsubscribeFromMeeting(id)
    }
}
