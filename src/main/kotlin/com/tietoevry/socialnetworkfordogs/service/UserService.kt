package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.dto.UserDto
import com.tietoevry.socialnetworkfordogs.entity.User
import com.tietoevry.socialnetworkfordogs.exception.auth.UserNotFoundException
import com.tietoevry.socialnetworkfordogs.mapper.mapToDto
import com.tietoevry.socialnetworkfordogs.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,
) {

    fun createUser(user: User): Long {
        return repository.save(user).id
    }

    fun updateUser(user: User): User {
        return repository.save(user)
    }

    fun deleteUser(id: Long) {
        repository.delete(getUser(id))
    }

    fun getUser(id: Long): User {
        return repository.findById(id).get()
    }
}
