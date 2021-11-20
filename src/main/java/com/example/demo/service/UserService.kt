package com.example.demo.service;

import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService {
    private lateinit var repository: UserRepository

    fun createUser(user: User): Long? {
        return repository.save(user).id
    }

    fun updateUser(user: User) {
        repository.save(user);

    }

    fun deleteUser(id: Long) {
        repository.delete(getUser(id))
    }

    fun getUser(id: Long): User {
        return repository.findById(id).get()
    }
}
