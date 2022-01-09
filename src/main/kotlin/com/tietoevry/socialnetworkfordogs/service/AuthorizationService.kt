package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.authorization.security.jwt.JwtTokenProvider
import com.tietoevry.socialnetworkfordogs.dto.UserDto
import com.tietoevry.socialnetworkfordogs.dto.UserLoginDto
import com.tietoevry.socialnetworkfordogs.exception.auth.UserAlreadyExistsException
import com.tietoevry.socialnetworkfordogs.exception.auth.UserNotFoundException
import com.tietoevry.socialnetworkfordogs.mapper.mapToEntity
import com.tietoevry.socialnetworkfordogs.repository.UserRepository
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthorizationService(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userService: UserService,
    private val userRepository: UserRepository,
) {

    companion object {
        private val logger = KLogging().logger
    }

    /**
     * Метод логирования пользователя
     *
     * @param userDto - DTO пользователя
     * @return - ResponseEntity с токеном в виде строки
     */
    @Transactional(readOnly = true)
    fun login(userDto: UserLoginDto): ResponseEntity<String> {
        logger.info { "Пользователь с логином: ${userDto.login} подал запрос на аутентификацию" }
        val user = userRepository.findByLogin(userDto.login).orElseThrow {
            throw UserNotFoundException("Пользователя с логином ${userDto.login} не существует")
        }
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userDto.login, userDto.password))
        val token = jwtTokenProvider.createToken(user.login)
        return ResponseEntity.ok(token)
    }

    /**
     * Метод регистрации пользователя
     * @param userDto - DTO пользователя
     * @return - ResponseEntity с идентификатором пользователя
     */
    @Transactional
    fun register(userDto: UserDto): ResponseEntity<Long> {
        logger.info { "Пользователь с логином ${userDto.login} подал запрос на регистрацию" }
        checkUserWithThisLoginNotExists(userDto.login)
        val userId = userService.createUser(userDto.mapToEntity())
        return ResponseEntity.ok(userId)
    }

    fun checkUserWithThisLoginNotExists(login: String) {
        if (userRepository.findByLogin(login).isPresent)
            throw UserAlreadyExistsException("Пользователь с логином $login уже существует")
    }
}
