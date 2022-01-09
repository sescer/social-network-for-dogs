package com.tietoevry.socialnetworkfordogs.authorization.security.jwt

import com.tietoevry.socialnetworkfordogs.exception.UserNotFoundException
import com.tietoevry.socialnetworkfordogs.repository.UserRepository
import mu.KLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * Сервис для аутентификации пользователя.
 */
@Service
class JwtUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    companion object {
        private val logger = KLogging().logger
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userName: String): UserDetails {
        val user = userRepository.findUserByMail(userName)
            .orElseThrow { throw UserNotFoundException("Пользователь $userName не найден") }
        val jwtUser = JwtUserFactory.create(user)
        logger.info { "JWT user for user with name: $userName was successfully created" }
        return jwtUser
    }
}
