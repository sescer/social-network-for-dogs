package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.entity.Dog
import com.tietoevry.socialnetworkfordogs.entity.Meeting
import com.tietoevry.socialnetworkfordogs.service.MeetingService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/meeting")
class MeetingController (
    private val service: MeetingService
) {
    @PostMapping("/create")
    fun createMeeting(meeting: Meeting): Long? {
        return service.createMeeting(meeting)
    }

    @GetMapping("/")
    fun getMeeting(id: Long): Meeting {
        return service.getMeeting(id)
    }

    @PostMapping("/update")
    fun updateMeeting(meeting: Meeting) {
        service.updateMeeting(meeting)
    }

    @DeleteMapping("/")
    fun deleteMeeting(id: Long) {
        service.deleteMeeting(id)
    }
}