package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Breed
import com.tietoevry.socialnetworkfordogs.entity.Dog
import com.tietoevry.socialnetworkfordogs.query.DogSearchQuery
import com.tietoevry.socialnetworkfordogs.entity.Sex
import com.tietoevry.socialnetworkfordogs.entity.color.Color
import com.tietoevry.socialnetworkfordogs.repository.DogRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.Locale

/**
 * Class represents dog service
 */
@Service
class DogService(
    private val repository: DogRepository,
) {
    /**
     * Method for creating dog
     *
     * @param dog - user's dog
     * @return - id of created dog
     */
    fun createDog(dog: Dog): Long {
        return repository.save(dog).id
    }
    /**
     * Method for updating dog information
     *
     * @param dog - user's dog information
     * @return - Dog
     */
    fun updateDog(dog: Dog) : Dog {
        return repository.save(dog)
    }
    /**
     * Method for deleting dog
     *
     * @param id - user's dog id
     * @return - id of deleted dog
     */
    fun deleteDog(id: Long) {
        repository.delete(getDog(id))
    }
    /**
     * Method for getting information of user's dog
     *
     * @param id - user's dog id
     * @return - id of user's dog
     */
    fun getDog(id: Long): Dog {
        return repository.findById(id).get()
    }
    /**
     * Method for searching dogs
     *
     * @param dogSearchQuery - searching query(by nickname/breed/color/sex/age)
     * @return - list of found dogs
     */
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
    /**
     * Method for creating query to db for searching dogs by nickname
     *
     * @param nickname - dog's nickname to search
     * @return - specification of found dogs
     */
    fun containsNickname(nickname: String?): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (nickname != null && nickname.isNotBlank()) {
                builder.like(builder.lower(root.get("nickname")), "%${nickname.lowercase(Locale.getDefault())}%")
            } else {
                null
            }
        }
    }
    /**
     * Method for creating query to db for searching dogs by age's interval
     *
     * @param startAge - start(interval) age of dog to search
     * @param endAge - end(interval) age of dog to search
     * @return - specification of found dogs
     */
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
    /**
     * Method for creating query to db for searching dogs by breed
     *
     * @param breed - dog's breed to search
     * @return - specification of found dogs
     */
    fun containsBreed(breed: Breed?): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (breed != null && breed != Breed.NONE)
                builder.equal(builder.lower(root.get<String?>("breed").`as`(String::class.java)), "${breed.ordinal}")
            else
                null
        }
    }
    /**
     * Method for creating query to db for searching dogs by color
     *
     * @param color - dog's color to search
     * @return - specification of found dogs
     */
    fun containsColor(color: Color?): Specification<Dog> {
        return Specification<Dog> { root, query, builder ->
            if (color != null && color != Color.NONE) {
                builder.equal(builder.lower(root.get<String?>("hairColor").`as`(String::class.java)), "${color.ordinal}")
            } else {
                null
            }
        }
    }
    /**
     * Method for creating query to db for searching dogs by sex
     *
     * @param sex - dog's sex to search
     * @return - specification of found dogs
     */
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
