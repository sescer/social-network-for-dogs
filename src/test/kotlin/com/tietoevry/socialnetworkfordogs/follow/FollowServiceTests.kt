package com.tietoevry.socialnetworkfordogs.follow

import com.tietoevry.socialnetworkfordogs.dto.DogDto
import com.tietoevry.socialnetworkfordogs.entity.Breed
import com.tietoevry.socialnetworkfordogs.entity.Follow
import com.tietoevry.socialnetworkfordogs.entity.FollowId
import com.tietoevry.socialnetworkfordogs.entity.Sex
import com.tietoevry.socialnetworkfordogs.entity.color.Color
import com.tietoevry.socialnetworkfordogs.mapper.mapToEntity
import com.tietoevry.socialnetworkfordogs.repository.DogRepository
import com.tietoevry.socialnetworkfordogs.repository.FollowRepository
import com.tietoevry.socialnetworkfordogs.service.DogService
import com.tietoevry.socialnetworkfordogs.service.FollowService
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.NoSuchElementException

@Testcontainers
@DisplayName("Tests of follow service")
@SpringBootTest
@ActiveProfiles("test")
class FollowServiceTests @Autowired constructor(
    private val followRepository: FollowRepository,
    private val followService: FollowService,
    private val dogService: DogService,
    private val dogRepository: DogRepository
) {

    private lateinit var testFollow12: Follow
    private lateinit var testFollow21: Follow
    private lateinit var testFollow13: Follow
    private lateinit var testFollow41: Follow

    private fun getTestDogDto(n: Int): DogDto {
        return DogDto(
            "name$n",
            Breed.CORGI,
            n,
            Color.BROWN,
            Sex.F
        )
    }

    private val dogDto1 = getTestDogDto(1)
    private val dogDto2 = getTestDogDto(2)
    private val dogDto3 = getTestDogDto(3)
    private val dogDto4 = getTestDogDto(4)

    private var dogId1 = 0L
    private var dogId2 = 0L
    private var dogId3 = 0L
    private var dogId4 = 0L

    @BeforeEach
    @Transactional
    fun createDogsAndSetupFollows() {
        dogId1 = dogService.createDog(dogDto1.mapToEntity())
        dogId2 = dogService.createDog(dogDto2.mapToEntity())
        dogId3 = dogService.createDog(dogDto3.mapToEntity())
        dogId4 = dogService.createDog(dogDto4.mapToEntity())

        testFollow12 = Follow(dogId1, dogId2)
        testFollow13 = Follow(dogId1, dogId3)
        testFollow21 = Follow(dogId2, dogId1)
        testFollow41 = Follow(dogId4, dogId1)
    }

    @AfterEach
    @Transactional
    fun deleteTestFollowFromDB() {
        followRepository.deleteAll()
    }

    @AfterEach
    @Transactional
    fun deleteTestDogs() {
        dogRepository.deleteAll()
    }

    @Test
    @DisplayName("Friend/follow creation request. Expected - success")
    fun createFollowTest() {
        val response = followService.createFollow(testFollow12)
        Assertions.assertEquals(testFollow12, response)
    }

    //TODO: maybe this should be removed
    //maybe even FollowService.getFollow() should be removed (it does approximately nothing)
    @Test
    @DisplayName("Get a follow. Expected - success")
    fun getFollowTest() {
        var response = followService.createFollow(testFollow12)
        Assertions.assertEquals(testFollow12, response)
        response = followService.getFollow(FollowId(dogId1, dogId2))
        Assertions.assertEquals(testFollow12, response)
    }

    @Test
    @DisplayName("Create and delete a follow, then try to request it. Expected - exception is thrown")
    fun deleteFollowTest() {
        val response = followService.createFollow(testFollow12)
        Assertions.assertEquals(testFollow12, response)
        followService.deleteFollow(FollowId(dogId1, dogId2))
        Assertions.assertThrows(NoSuchElementException::class.java) {
            followService.getFollow(FollowId(dogId1, dogId2))
        }
    }

    @Test
    @DisplayName("Get friend list of user 1. Expecting - success")
    fun listOfFriendsTest() {
        var response = followService.createFollow(testFollow12)
        Assertions.assertEquals(testFollow12, response)
        response = followService.createFollow(testFollow21)
        Assertions.assertEquals(testFollow21, response)
        response = followService.createFollow(testFollow13)
        Assertions.assertEquals(testFollow13, response)
        response = followService.createFollow(testFollow41)
        Assertions.assertEquals(testFollow41, response)

        val friendsList:List<Long> = followService.listOfFriends(dogId1)
        val expectedList:List<Long> = listOf(dogId2)
        Assertions.assertTrue(
            friendsList.size == expectedList.size
                    && friendsList.containsAll(expectedList) && expectedList.containsAll(friendsList)
        )

    }

    @Test
    @DisplayName("Get followers list of user 1. Expecting - success")
    fun listOfFollowersTest() {
        var response = followService.createFollow(testFollow12)
        Assertions.assertEquals(testFollow12, response)
        response = followService.createFollow(testFollow21)
        Assertions.assertEquals(testFollow21, response)
        response = followService.createFollow(testFollow13)
        Assertions.assertEquals(testFollow13, response)
        response = followService.createFollow(testFollow41)
        Assertions.assertEquals(testFollow41, response)

        val followersList:List<Long> = followService.listOfFollowers(dogId1)
        val expectedList:List<Long> = listOf(dogId4)
        Assertions.assertTrue(
            followersList.size == expectedList.size
                    && followersList.containsAll(expectedList) && expectedList.containsAll(followersList)
        )

    }

    @Test
    @DisplayName("Get followings list of user 1. Expecting - success")
    fun listOfFollowingsTest() {
        var response = followService.createFollow(testFollow12)
        Assertions.assertEquals(testFollow12, response)
        response = followService.createFollow(testFollow21)
        Assertions.assertEquals(testFollow21, response)
        response = followService.createFollow(testFollow13)
        Assertions.assertEquals(testFollow13, response)
        response = followService.createFollow(testFollow41)
        Assertions.assertEquals(testFollow41, response)

        val followingsList:List<Long> = followService.listOfFollowings(dogId1)
        val expectedList:List<Long> = listOf(dogId3)
        Assertions.assertTrue(
            followingsList.size == expectedList.size
                    && followingsList.containsAll(expectedList) && expectedList.containsAll(followingsList)
        )

    }


}