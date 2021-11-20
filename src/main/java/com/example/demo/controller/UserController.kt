package com.example.demo.controller

import com.example.demo.entity.User
import com.example.demo.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
class UserController {
    private lateinit var service: UserService;

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