package com.tietoevry.socialnetworkfordogs.authorization.security.jwt

import com.tietoevry.socialnetworkfordogs.entity.User

object JwtUserFactory {
    fun create(user: User): JwtUser {
        return JwtUser(user.id, user.mail, user.password)
    }
}
