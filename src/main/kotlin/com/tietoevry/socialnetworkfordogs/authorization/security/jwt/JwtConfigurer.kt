package com.tietoevry.socialnetworkfordogs.authorization.security.jwt

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtConfigurer(private val jwtTokenProvider: JwtTokenProvider) :
    SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.run {
            addFilterBefore(JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
        }
    }
}
