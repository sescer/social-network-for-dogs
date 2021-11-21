package com.tietoevry.socialnetworkfordogs.configuration.security

import com.tietoevry.socialnetworkfordogs.authorization.security.jwt.JwtConfigurer
import com.tietoevry.socialnetworkfordogs.authorization.security.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

/**
 * Класс конфигурации веб секьюрити.
 */
@Configuration
class SecurityConfiguration(
    private val jwtTokenProvider: JwtTokenProvider,
) : WebSecurityConfigurerAdapter() {

    companion object {
        const val LOGIN_ENDPOINT = "/api/users/login"
        const val REGISTER_ENDPOINT = "/api/users/register"
        private val AUTH_WHITELIST = arrayOf("/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/actuator")
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(LOGIN_ENDPOINT).permitAll()
            .antMatchers(REGISTER_ENDPOINT).permitAll()
            .antMatchers(*AUTH_WHITELIST).permitAll()
            .anyRequest().authenticated()
            .and()
            .apply(JwtConfigurer(jwtTokenProvider))
    }
}
