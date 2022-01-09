package com.tietoevry.socialnetworkfordogs.authorization.security.jwt

import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class JwtTokenFilter(private val jwtTokenProvider: JwtTokenProvider) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse?, filterChain: FilterChain) {
        val resolvedToken = jwtTokenProvider.resolveToken(request as HttpServletRequest)
        resolvedToken?.takeIf { token ->
            jwtTokenProvider.validateToken(token)
        }?.let { token ->
            jwtTokenProvider.getAuthentication(token).let { authentication ->
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }
}
