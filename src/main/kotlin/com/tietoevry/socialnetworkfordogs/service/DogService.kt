package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Breed
import com.tietoevry.socialnetworkfordogs.entity.Dog
import com.tietoevry.socialnetworkfordogs.query.DogSearchQuery
import com.tietoevry.socialnetworkfordogs.entity.Sex
import com.tietoevry.socialnetworkfordogs.entity.color.Color
import com.tietoevry.socialnetworkfordogs.repository.DogRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*


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

    fun searchDog(dogSearchQuery: DogSearchQuery): List<Dog> {
        return repository.findAll(
            isInAgeRange(dogSearchQuery.startAge, dogSearchQuery.endAge)
                .and(containsNickname(dogSearchQuery.nickname)
                    .and(containsBreed(dogSearchQuery.breed)
                        .and(containsColor(dogSearchQuery.hairColor)
                            .and(containsSex(dogSearchQuery.sex))
                        )
                    )
                )
        )
    }

    fun containsNickname(nickname: String?): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (nickname != null && nickname.isNotBlank()) {
                builder.like(builder.lower(root.get("nickname")), "%${nickname.lowercase(Locale.getDefault())}%")
            } else {
                null
            }
        }
    }

    fun isInAgeRange(startAge: Int?, endAge: Int?): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (startAge == null && endAge == null)
                null
            else if (startAge == null)
                builder.lessThanOrEqualTo(root.get("age"), endAge!!)
            else if (endAge == null)
                builder.greaterThanOrEqualTo(root.get("age"), startAge)
            else
                builder.between(root.get("age"), startAge, endAge)
        }
    }

    fun containsBreed(breed: Breed?): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (breed != null && breed != Breed.NONE)
                builder.equal(builder.lower(root.get<String?>("breed").`as`(String::class.java)), "${breed.ordinal}")
            else
                null
        }
    }
    fun containsColor(color: Color?): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (color != null && color != Color.NONE) {
                builder.equal(builder.lower(root.get<String?>("hairColor").`as`(String::class.java)), "${color.ordinal}")
            } else {
                null
            }
        }
    }

    fun containsSex(sex: Sex?): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (sex != null && sex != Sex.NONE) {
                builder.equal(builder.lower(root.get<String?>("sex").`as`(String::class.java)), "${sex.ordinal}")
            } else {
                null
            }
        }
    }

}
