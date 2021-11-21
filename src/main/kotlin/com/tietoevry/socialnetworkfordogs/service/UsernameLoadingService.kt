package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.authorization.security.jwt.JwtUserFactory
import com.tietoevry.socialnetworkfordogs.exception.auth.UserNotFoundException
import com.tietoevry.socialnetworkfordogs.repository.UserRepository
import mu.KLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsernameLoadingService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    companion object {
        private val logger = KLogging().logger
    }

    override fun loadUserByUsername(login: String): UserDetails {
        val user = userRepository.findByLogin(login).orElseThrow {
            throw UserNotFoundException("Пользователь с логином $login не найден.")
        }
        val jwtUser = JwtUserFactory.create(user)
        logger.debug { "JWTUser был успешно создан!" }
        return jwtUser
    }
}
