package com.tietoevry.socialnetworkfordogs.auth

import com.tietoevry.socialnetworkfordogs.dto.UserDto
import com.tietoevry.socialnetworkfordogs.dto.UserLoginDto
import com.tietoevry.socialnetworkfordogs.exception.auth.UserAlreadyExistsException
import com.tietoevry.socialnetworkfordogs.exception.auth.UserNotFoundException
import com.tietoevry.socialnetworkfordogs.repository.UserRepository
import com.tietoevry.socialnetworkfordogs.service.AuthorizationService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.junit.jupiter.Testcontainers


@Testcontainers
@DisplayName("Тесты на авторизацию")
@SpringBootTest
@ActiveProfiles("test")
class AuthorizationServiceTests @Autowired constructor(
    private val authorizationService: AuthorizationService,
    private val userRepository: UserRepository,
) {

    private val testUser = UserDto(
        "test",
        "test",
        "test",
        "test",
        "test",
        "test",
    )

    private val testLoginUser = UserLoginDto(
        "test",
        "test",
    )

    @AfterEach
    @Transactional
    fun deleteTestUserFromDB() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("Тест на регистрацию пользователя. Ожидается успех")
    @Transactional
    fun registerUser() {
        val response = authorizationService.register(testUser)
        Assertions.assertEquals(response.statusCode, HttpStatus.OK)
        Assertions.assertTrue(response.hasBody())
    }

    @Test
    @DisplayName("Тест на регистрацию одного и того же пользователя дважды и последующий вылет UserAlreadyExistsException. Ожидается успех")
    @Transactional
    fun registerUserThatAlreadyRegistered() {
        authorizationService.register(testUser)
        Assertions.assertThrows(UserAlreadyExistsException::class.java) {
            authorizationService.register(testUser)
        }
    }

    @Test
    @DisplayName("Тест на логирования пользователя, который не зарегистрирован, и последующий вылет UserNotFoundException. Ожидается успех")
    @Transactional
    fun loginUserThatNotRegistered() {
        Assertions.assertThrows(UserNotFoundException::class.java) {
            authorizationService.login(testLoginUser)
        }
    }
}
