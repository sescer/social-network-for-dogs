package com.tietoevry.socialnetworkfordogs.user

import com.tietoevry.socialnetworkfordogs.dto.UserDto
import com.tietoevry.socialnetworkfordogs.mapper.mapToDto
import com.tietoevry.socialnetworkfordogs.mapper.mapToEntity
import com.tietoevry.socialnetworkfordogs.repository.UserRepository
import com.tietoevry.socialnetworkfordogs.service.UserService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DisplayName("Tests of user service")
@SpringBootTest
@ActiveProfiles("test")
class UserServiceTests @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {
    private val testUser = UserDto(
        "test",
        "test",
        "test",
        "test",
        "test",
        "test",
    )
    private val testUpdateUser = UserDto(
        "tset",
        "test",
        "tset",
        "tset",
        "tset",
        "tset",
    )


    @AfterEach
    @Transactional
    fun deleteTestMessageFromDB() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("Creating user. Expecting - success")
    @Transactional
    fun createUserTest(){
        val createdUserId = userService.createUser(testUser.mapToEntity())
        Assertions.assertEquals(testUser, userRepository.findById(createdUserId).get().mapToDto())
    }
    @Test
    @DisplayName("Updating user info. Expecting - success")
    @Transactional
    fun updateUserTest(){
        val createdUserId = userService.createUser(testUser.mapToEntity())
        Assertions.assertEquals(testUser, userRepository.findById(createdUserId).get().mapToDto())

        val actual = userService.updateUser(testUpdateUser.mapToEntity())
        Assertions.assertEquals(testUpdateUser, actual.mapToDto())

    }
    @Test
    @DisplayName("Getting user info. Expecting - success")
    @Transactional
    fun getUserTest(){
        val createdUserId = userService.createUser(testUser.mapToEntity())
        val actual = userService.getUser(createdUserId)
        Assertions.assertEquals(testUser, actual.mapToDto())
    }

    @Test
    @DisplayName("Deleting user. Expecting - success")
    @Transactional
    fun deleteUserTest(){
        val createdUserId = userService.createUser(testUser.mapToEntity())
         userService.deleteUser(createdUserId)
        Assertions.assertThrows(NoSuchElementException::class.java){
            userService.getUser(createdUserId).mapToDto()
        }
    }
}