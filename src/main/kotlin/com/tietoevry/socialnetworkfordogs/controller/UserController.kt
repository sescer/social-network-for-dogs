package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.entity.User
import com.tietoevry.socialnetworkfordogs.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/user")
class UserController(
    private val service: UserService,
) {
    @PostMapping("/create")
    fun createUser(@RequestBody user: User) {
        service.createUser(user)
    }

    @PostMapping("/update")
    fun updateUser(@RequestBody user: User) {
        service.updateUser(user)
    }

    @DeleteMapping("/")
    fun deleteUser(@RequestBody id: Long) {
        service.deleteUser(id)
    }

    @GetMapping("/")
    fun getUser(@RequestBody id: Long): User? {
        return service.getUser(id)
    }
}