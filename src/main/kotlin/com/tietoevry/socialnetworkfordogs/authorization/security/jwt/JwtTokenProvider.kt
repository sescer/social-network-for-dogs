package com.tietoevry.socialnetworkfordogs.authorization.security.jwt

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Base64
import java.util.Date
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * Класс настройки провайдера. Можно настраивать время валидации, а также ключ.
 */
@Component
class JwtTokenProvider {
    @Value("\${jwt.token.secret}")
    private var secret: String? = null

    @Value("\${jwt.token.expired}")
    private val validityInMilliseconds: Long = 0

    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @PostConstruct
    protected fun init() {
        secret = Base64.getEncoder().encodeToString(secret!!.toByteArray())
    }

    fun createToken(username: String?): String {
        val claims = Jwts.claims().setSubject(username)
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun getAuthentication(token: String?): Authentication {
        val userDetails = userDetailsService!!.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String?): String {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader(AUTH_HEADER_ATTRIBUTE_NAME)
        return if (bearerToken != null && bearerToken.startsWith(AUTH_HEADER_START_STRING)) {
            bearerToken.substring(AUTH_HEADER_START_STRING.length)
        } else null
    }

    fun validateToken(token: String?): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    companion object {
        private const val AUTH_HEADER_START_STRING = "Bearer "
        private const val AUTH_HEADER_ATTRIBUTE_NAME = "Authorization"
    }
}