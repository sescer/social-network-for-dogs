package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.entity.Dog
import com.tietoevry.socialnetworkfordogs.entity.DogSearchQuery
import com.tietoevry.socialnetworkfordogs.service.DogService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/dog")
class DogController(
    private val service: DogService,
) {

    @PostMapping("/create")
    fun createDog(@RequestBody dog: Dog): Long? {
        return service.createDog(dog)
    }

    @PostMapping("/update")
    fun updateDog(@RequestBody dog: Dog) {
        service.updateDog(dog)
    }

    @DeleteMapping("/")
    fun deleteDog(@RequestBody id: Long) {
        service.deleteDog(id)
    }

    @GetMapping("/")
    fun getDog(@RequestBody id: Long): Dog {
        return service.getDog(id)
    }

    @PostMapping("/search")
    fun search(@RequestBody dogSearchQuery: DogSearchQuery): List<Dog> {
        return service.searchDog(dogSearchQuery)
    }
}