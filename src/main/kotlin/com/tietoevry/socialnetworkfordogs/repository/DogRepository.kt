package com.tietoevry.socialnetworkfordogs.repository

import com.tietoevry.socialnetworkfordogs.entity.Dog
import org.springframework.data.repository.CrudRepository

interface DogRepository : CrudRepository<Dog, Long>