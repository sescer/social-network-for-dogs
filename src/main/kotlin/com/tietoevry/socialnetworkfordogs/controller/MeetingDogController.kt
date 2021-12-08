package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.service.MeetingDogService
import org.springframework.web.bind.annotation.RestController

//TODO
@RestController
class MeetingDogController (
    private val service: MeetingDogService
) {

}