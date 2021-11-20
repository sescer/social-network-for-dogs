package com.example.demo.service;

import com.example.demo.entity.Dog
import com.example.demo.repository.DogRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class DogService {
    private lateinit var repository: DogRepository

    fun createDog(dog: Dog): Long? {
        return repository.save(dog).id
    }

    fun updateDog(dog: Dog) {
        repository.save(dog);

    }

    fun deleteDog(id: Long) {
        repository.delete(getDog(id))
    }

    fun getDog(id: Long): Dog {
        return repository.findById(id).get()
    }
}
