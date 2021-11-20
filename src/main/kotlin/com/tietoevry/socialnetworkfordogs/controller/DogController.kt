package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.entity.Dog
import com.tietoevry.socialnetworkfordogs.service.DogService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/dog")
class DogController(
    private val service: DogService,
) {

    @PostMapping("/create")
    fun createDog(dog: Dog): Long? {
        return service.createDog(dog)
    }

    @PostMapping("/update")
    fun updateDog(dog: Dog) {
        service.updateDog(dog)
    }

    @DeleteMapping("/")
    fun deleteDog(id: Long) {
        service.deleteDog(id)
    }

    @GetMapping("/")
    fun getDog(id: Long): Dog {
        return service.getDog(id)
    }
}