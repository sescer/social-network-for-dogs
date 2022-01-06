package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.entity.Follow
import com.tietoevry.socialnetworkfordogs.entity.FollowId
import com.tietoevry.socialnetworkfordogs.service.FollowService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/follow")
class FollowController(
    private val service: FollowService
) {
    @PostMapping("/create")
    fun createFollow(@RequestBody follow: Follow) {
        service.createFollow(follow)
    }

    @DeleteMapping("/")
    fun deleteFollow(@RequestBody followId: FollowId) {
        service.deleteFollow(followId)
    }

    @GetMapping("{id}/friends")
    fun findFriends(@PathVariable id: Long): List<Long> {
        return service.listOfFriends(id)
    }

    @GetMapping("{id}/followers")
    fun findFollowers(@PathVariable id: Long): List<Long> {
        return service.listOfFollowers(id)
    }

    @GetMapping("{id}/followings")
    fun findFollowings(@PathVariable id: Long): List<Long> {
        return service.listOfFollowings(id)
    }
}