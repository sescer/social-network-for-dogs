package com.tietoevry.socialnetworkfordogs.dog

import com.tietoevry.socialnetworkfordogs.dto.DogDto
import com.tietoevry.socialnetworkfordogs.entity.Breed
import com.tietoevry.socialnetworkfordogs.entity.Sex
import com.tietoevry.socialnetworkfordogs.entity.color.Color
import com.tietoevry.socialnetworkfordogs.mapper.mapToDto
import com.tietoevry.socialnetworkfordogs.mapper.mapToEntity
import com.tietoevry.socialnetworkfordogs.query.DogSearchQuery
import com.tietoevry.socialnetworkfordogs.repository.DogRepository
import com.tietoevry.socialnetworkfordogs.service.DogService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DisplayName("Tests of dog service")
@SpringBootTest
@ActiveProfiles("test")
class DogServiceTests @Autowired constructor(
    private val dogRepository: DogRepository,
    private val dogService: DogService
) {
    private val testDog = DogDto(
        "test",
        Breed.NONE,
        1,
        Color.NONE,
        Sex.NONE
    )
    private val testUpdateDog = DogDto(
        "update",
        Breed.CORGI,
        10,
        Color.BLACK,
        Sex.F
    )
    private val testAllDogSearchQuery = DogSearchQuery(
        "upd",
        1,
        11,
        Breed.CORGI,
        Color.BLACK,
        Sex.F
    )
    private val testNicknameDogSearchQuery = DogSearchQuery(
        "upd",
        null,
        null,
        null,
        null,
        null
    )
    private val testEndAgeDogSearchQuery = DogSearchQuery(
        null,
        null,
        12,
        null,
        null,
        null
    )
    private val testStartAgeDogSearchQuery = DogSearchQuery(
        null,
        2,
        null,
        null,
        null,
        null
    )
    private val testAgeRangeDogSearchQuery = DogSearchQuery(
        null,
        12,
        null,
        null,
        null,
        null
    )
    private val testEnumColorDogSearchQuery = DogSearchQuery(
        null,
        null,
        null,
        Breed.CORGI,
        null,
        null
    )


    @AfterEach
    @Transactional
    fun deleteTestMessageFromDB() {
        dogRepository.deleteAll()
    }

    @Test
    @DisplayName("Create dog. Expected - success")
    fun createDogTest() {
        val createdDogId = dogService.createDog(testDog.mapToEntity())
        Assertions.assertEquals(testDog, dogRepository.findById(createdDogId).get().mapToDto())
    }
    @Test
    @DisplayName("Create and update dog info. Expected - success")
    fun updateDogTest() {
        val createdDogId = dogService.createDog(testDog.mapToEntity())
        Assertions.assertEquals(testDog, dogRepository.findById(createdDogId).get().mapToDto())

        val actual = dogService.updateDog(testUpdateDog.mapToEntity())
        Assertions.assertEquals(testUpdateDog, actual.mapToDto())

    }
    @Test
    @DisplayName("Create and get dog. Expected - success")
    fun getDogTest() {
        val createdDogId = dogService.createDog(testDog.mapToEntity())
        val actual = dogService.getDog(createdDogId)
        Assertions.assertEquals(testDog, actual.mapToDto())
    }

    @Test
    @DisplayName("Create and delete dog. Expected - Expected throws")
    fun deleteDogTest() {
        val createdDogId = dogService.createDog(testDog.mapToEntity())
        dogService.deleteDog(createdDogId)
        Assertions.assertThrows(NoSuchElementException::class.java){
            dogService.getDog(createdDogId).mapToDto()
        }
    }
    @Test
    @DisplayName("Create two dogs and searching dog by all parameters. Expected - success")
    fun searchDogByAllTest() {
        val createdDogId1 = dogService.createDog(testDog.mapToEntity())
        Assertions.assertEquals(testDog, dogRepository.findById(createdDogId1).get().mapToDto())
        val createdDogId2 = dogService.createDog(testUpdateDog.mapToEntity())
        Assertions.assertEquals(testUpdateDog, dogRepository.findById(createdDogId2).get().mapToDto())

        val dogList = dogService.searchDog(testAllDogSearchQuery)
        Assertions.assertTrue(dogList.size == 1
                && dogList.first().mapToDto() == testUpdateDog)
    }
    @Test
    @DisplayName("Create two dogs and searching dog by nickname. Expected - success")
    fun searchDogByNicknameTest() {
        val createdDogId1 = dogService.createDog(testDog.mapToEntity())
        Assertions.assertEquals(testDog, dogRepository.findById(createdDogId1).get().mapToDto())
        val createdDogId2 = dogService.createDog(testUpdateDog.mapToEntity())
        Assertions.assertEquals(testUpdateDog, dogRepository.findById(createdDogId2).get().mapToDto())

        val dogList = dogService.searchDog(testNicknameDogSearchQuery)
        Assertions.assertTrue(dogList.size == 1
                && dogList.first().mapToDto() == testUpdateDog)
    }
    @Test
    @DisplayName("Create two dogs and searching dog by end age. Expected - success")
    fun searchDogByEndAgeTest() {
        val createdDogId1 = dogService.createDog(testDog.mapToEntity())
        Assertions.assertEquals(testDog, dogRepository.findById(createdDogId1).get().mapToDto())
        val createdDogId2 = dogService.createDog(testUpdateDog.mapToEntity())
        Assertions.assertEquals(testUpdateDog, dogRepository.findById(createdDogId2).get().mapToDto())

        val dogList = dogService.searchDog(testEndAgeDogSearchQuery)
        Assertions.assertTrue(dogList.size == 2
                && dogList[0].mapToDto() == testDog
                && dogList[1].mapToDto() == testUpdateDog)
    }
    @Test
    @DisplayName("Create two dogs and searching dog by start age. Expected - success")
    fun searchDogByStartAgeTest() {
        val createdDogId1 = dogService.createDog(testDog.mapToEntity())
        Assertions.assertEquals(testDog, dogRepository.findById(createdDogId1).get().mapToDto())
        val createdDogId2 = dogService.createDog(testUpdateDog.mapToEntity())
        Assertions.assertEquals(testUpdateDog, dogRepository.findById(createdDogId2).get().mapToDto())

        val dogList = dogService.searchDog(testStartAgeDogSearchQuery)
        Assertions.assertTrue(dogList.size == 1
                && dogList.first().mapToDto() == testUpdateDog)
    }
    @Test
    @DisplayName("Searching dog by age range. Expected - success")
    fun searchDogByAgeRangeTest() {
        val createdDogId1 = dogService.createDog(testDog.mapToEntity())
        Assertions.assertEquals(testDog, dogRepository.findById(createdDogId1).get().mapToDto())
        val createdDogId2 = dogService.createDog(testUpdateDog.mapToEntity())
        Assertions.assertEquals(testUpdateDog, dogRepository.findById(createdDogId2).get().mapToDto())

        val dogList = dogService.searchDog(testAgeRangeDogSearchQuery)
        Assertions.assertTrue(dogList.isEmpty());
    }
    @Test
    @DisplayName("Create two dogs and searching dog by color. Expected - success")
    fun searchDogByEnumColorTest() {
        val createdDogId1 = dogService.createDog(testDog.mapToEntity())
        Assertions.assertEquals(testDog, dogRepository.findById(createdDogId1).get().mapToDto())
        val createdDogId2 = dogService.createDog(testUpdateDog.mapToEntity())
        Assertions.assertEquals(testUpdateDog, dogRepository.findById(createdDogId2).get().mapToDto())

        val dogList = dogService.searchDog(testEnumColorDogSearchQuery)
        Assertions.assertTrue(dogList.size == 1
                && dogList.first().mapToDto() == testUpdateDog)
    }
}