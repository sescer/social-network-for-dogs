package com.tietoevry.socialnetworkfordogs.authorization.security.jwt

import com.tietoevry.socialnetworkfordogs.authorization.security.jwt.entity.JwtUser
import com.tietoevry.socialnetworkfordogs.entity.User

/**
 * Класс фабрика для создания [JwtUser]
 */
class JwtUserFactory {

    companion object {
        fun create(user: User): JwtUser {
            return JwtUser(user.id, user.mail, user.password)
        }
    }
}
