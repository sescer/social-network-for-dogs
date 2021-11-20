package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Dog
import com.tietoevry.socialnetworkfordogs.repository.DogRepository
import org.springframework.stereotype.Service

@Service
class DogService(
    private val repository: DogRepository,
) {

    fun createDog(dog: Dog): Long? {
        return repository.save(dog).id
    }

    fun updateDog(dog: Dog) : Dog {
        return repository.save(dog)
    }

    fun deleteDog(id: Long) {
        repository.delete(getDog(id))
    }

    fun getDog(id: Long): Dog {
        return repository.findById(id).get()
    }
}
