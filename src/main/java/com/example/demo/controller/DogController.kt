package com.example.demo.controller

import com.example.demo.entity.Dog
import com.example.demo.service.DogService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/dog")
class DogController {
    private lateinit var service: DogService;


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