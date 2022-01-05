package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.User
import com.tietoevry.socialnetworkfordogs.repository.UserRepository
import org.springframework.stereotype.Service
/**
 * Class represents user service
 */
@Service
class UserService(
    private val repository: UserRepository,
) {
    /**
     * Method for creating user
     *
     * @param user - user information
     * @return - id of created user
     */
    fun createUser(user: User): Long {
        return repository.save(user).id
    }
    /**
     * Method for updating user information
     *
     * @param user - user information to update
     * @return - user information
     */
    fun updateUser(user: User): User {
        return repository.save(user)
    }
    /**
     * Method for deleting user
     *
     * @param id - user to delete
     */
    fun deleteUser(id: Long) {
        repository.delete(getUser(id))
    }
    /**
     * Method for getting user information by id
     *
     * @param id - user id
     * @return - user information
     */
    fun getUser(id: Long): User {
        return repository.findById(id).get()
    }
}
