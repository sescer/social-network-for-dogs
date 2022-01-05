package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.entity.User
import com.tietoevry.socialnetworkfordogs.service.UserService
import org.springframework.web.bind.annotation.*

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
    fun getUser(@PathVariable id: Long): User? {
        return service.getUser(id)
    }
}