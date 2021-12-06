package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Breed
import com.tietoevry.socialnetworkfordogs.entity.Dog
import com.tietoevry.socialnetworkfordogs.entity.DogSearchQuery
import com.tietoevry.socialnetworkfordogs.entity.Sex
import com.tietoevry.socialnetworkfordogs.entity.color.Color
import com.tietoevry.socialnetworkfordogs.repository.DogRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.criteria.Root


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

    fun searchDog(dogSearchQuery:DogSearchQuery): List<Dog> {
        return repository.findAll(
            isInDateRange(dogSearchQuery.startAge, dogSearchQuery.endAge)
                .and(containsNickname(dogSearchQuery.nickname)
                    .and(containsBreed(dogSearchQuery.breed)
                        .and(containsColor(dogSearchQuery.color)
                            .and(containsSex(dogSearchQuery.sex))
                        )
                    )
                )
        )
    }

    fun containsNickname(nickname: String): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (nickname.isNotBlank()) {
                builder.like(builder.lower(root.get("nickname")), "%${nickname.lowercase(Locale.getDefault())}%")
            } else {
                null
            }
        }
    }

    fun isInDateRange(startAge: Int, endAge: Int): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            builder.between(root.get("age"), startAge, endAge)
        }
    }

    fun containsBreed(breed: Breed): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (breed != Breed.NONE) {
                builder.equal(builder.lower(root.get<String?>("breed").`as`(String::class.java)), "${breed.ordinal}")
            } else {
                null
            }
        }
    }
    fun containsColor(color: Color): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (color != Color.NONE) {
                builder.equal(builder.lower(root.get<String?>("hairColor").`as`(String::class.java)), "${color.ordinal}")
            } else {
                null
            }
        }
    }

    fun containsSex(sex: Sex): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (sex != Sex.NONE) {
                builder.equal(builder.lower(root.get<String?>("sex").`as`(String::class.java)), "${sex.ordinal}")
            } else {
                null
            }
        }
    }

}
