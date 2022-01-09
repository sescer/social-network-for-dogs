package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.configuration.security.SecurityConfiguration
import com.tietoevry.socialnetworkfordogs.dto.UserDto
import com.tietoevry.socialnetworkfordogs.dto.UserLoginDto
import com.tietoevry.socialnetworkfordogs.service.AuthorizationService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorizationController(
    private val authorizationService: AuthorizationService,
) {


    @PostMapping(SecurityConfiguration.REGISTER_ENDPOINT)
    fun register(@RequestBody @Validated userDto: UserDto): ResponseEntity<Long> {
        return authorizationService.register(userDto)
    }

    @PostMapping(SecurityConfiguration.LOGIN_ENDPOINT)
    fun login(@RequestBody @Validated userDto: UserLoginDto): ResponseEntity<String> {
        return authorizationService.login(userDto)
    }
}
