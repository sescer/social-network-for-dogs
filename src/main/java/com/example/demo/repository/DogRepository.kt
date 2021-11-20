package com.example.demo.repository;

import com.example.demo.entity.Dog
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DogRepository : CrudRepository<Dog, Long>