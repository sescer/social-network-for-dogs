package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.entity.MeetingDog
import com.tietoevry.socialnetworkfordogs.entity.MeetingDogId
import com.tietoevry.socialnetworkfordogs.service.MeetingDogService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
