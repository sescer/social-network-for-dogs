package com.tietoevry.socialnetworkfordogs.meeting

import com.tietoevry.socialnetworkfordogs.dto.DogDto
import com.tietoevry.socialnetworkfordogs.dto.MeetingDto
import com.tietoevry.socialnetworkfordogs.entity.Breed
import com.tietoevry.socialnetworkfordogs.entity.Meeting
import com.tietoevry.socialnetworkfordogs.entity.MeetingStatus
import com.tietoevry.socialnetworkfordogs.entity.Sex
import com.tietoevry.socialnetworkfordogs.entity.color.Color
import com.tietoevry.socialnetworkfordogs.mapper.mapToDto
import com.tietoevry.socialnetworkfordogs.mapper.mapToEntity
import com.tietoevry.socialnetworkfordogs.query.MeetingDogSearchQuery
import com.tietoevry.socialnetworkfordogs.repository.DogRepository
import com.tietoevry.socialnetworkfordogs.repository.MeetingRepository
import com.tietoevry.socialnetworkfordogs.service.DogService
import com.tietoevry.socialnetworkfordogs.service.MeetingService
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.Duration
import java.time.Instant

@Testcontainers
@DisplayName("Meeting service tests")
@SpringBootTest
@ActiveProfiles("test")
class MeetingServiceTests @Autowired constructor(
    val meetingService: MeetingService,
    val meetingRepository: MeetingRepository,
    val dogService: DogService,
    val dogRepository: DogRepository
) {
    private lateinit var meetingDto1: MeetingDto
    private lateinit var meetingDto2: MeetingDto
    private lateinit var meetingDto3: MeetingDto

    private fun getTestDogDto(n: Int): DogDto {
        return DogDto(
            "name$n",
            Breed.CORGI,
            n,
            Color.BROWN,
            Sex.F
        )
    }

    private fun getTestMeetingDto(n: Int, hours: Long, authorId: Long): MeetingDto {
        return MeetingDto(
            "test$n",
            Instant.now() + Duration.ofHours(hours),
            authorId,
            MeetingStatus.NONE
        )
    }

    private val dogDto1 = getTestDogDto(1)
    private val dogDto2 = getTestDogDto(2)
    private val dogDto3 = getTestDogDto(3)

    private var dogId1 = 0L
    private var dogId2 = 0L
    private var dogId3 = 0L

    @BeforeEach
    @Transactional
    fun createTestMeetingsAndDogs() {
        dogId1 = dogService.createDog(dogDto1.mapToEntity())
        dogId2 = dogService.createDog(dogDto2.mapToEntity())
        dogId3 = dogService.createDog(dogDto3.mapToEntity())

        meetingDto1 = getTestMeetingDto(1, 5, dogId1)
        meetingDto2 = getTestMeetingDto(2, 3, dogId2)
        meetingDto3 = getTestMeetingDto(3, 7, dogId3)
    }

    @AfterEach
    @Transactional
    fun deleteTestMeetingsAndDogs() {
        meetingRepository.deleteAll()
        dogRepository.deleteAll()
    }

    @Test
    @DisplayName("Meeting creation test")
    fun createMeeting() {
        val response = meetingService.createMeeting(meetingDto1.mapToEntity())
        Assertions.assertNotNull(response)
    }

    @Test
    @DisplayName("Meeting update test")
    fun updateMeeting() {
        val response = meetingService.createMeeting(meetingDto1.mapToEntity())
        Assertions.assertNotNull(response)
        Assertions.assertEquals(meetingService.updateMeeting(meetingDto2.mapToEntity()).mapToDto(), meetingDto2)
    }

    @Test
    @DisplayName("Meeting deletion test")
    fun deleteMeeting() {
        val response = meetingService.createMeeting(meetingDto1.mapToEntity())
        Assertions.assertNotNull(response)
        meetingService.deleteMeeting(response!!)
        Assertions.assertThrows(NoSuchElementException::class.java) {
            meetingService.getMeeting(response)
        }
    }

    val lowerBoundQuery = MeetingDogSearchQuery(
        null,
        Instant.now() + Duration.ofHours(4),
        null
    )

    @Test
    @DisplayName("Meeting search by lower bound time")
    fun searchMeetingsByLowerBound() {
        var response = meetingService.createMeeting(meetingDto1.mapToEntity())
        Assertions.assertNotNull(response)
        response = meetingService.createMeeting(meetingDto2.mapToEntity())
        Assertions.assertNotNull(response)
        response = meetingService.createMeeting(meetingDto3.mapToEntity())
        Assertions.assertNotNull(response)

        val expected = listOf(meetingDto1.name, meetingDto3.name)

        val actual = meetingService.searchMeetingDog(lowerBoundQuery).map{
            it.mapToDto().name
        }

        Assertions.assertTrue(actual.size == expected.size && actual.containsAll(expected))
    }

    val higherBoundQuery = MeetingDogSearchQuery(
        null,
        null,
        Instant.now() + Duration.ofHours(6)
    )

    @Test
    @DisplayName("Meeting search by upper bound time")
    fun searchMeetingsByHigherBound() {
        var response = meetingService.createMeeting(meetingDto1.mapToEntity())
        Assertions.assertNotNull(response)
        response = meetingService.createMeeting(meetingDto2.mapToEntity())
        Assertions.assertNotNull(response)
        response = meetingService.createMeeting(meetingDto3.mapToEntity())
        Assertions.assertNotNull(response)

        val expected = listOf(meetingDto1.name, meetingDto2.name)

        val actual = meetingService.searchMeetingDog(higherBoundQuery).map{
            it.mapToDto().name
        }

        Assertions.assertTrue(actual.size == expected.size && actual.containsAll(expected))
    }

    val bothBoundQuery = MeetingDogSearchQuery(
        null,
        Instant.now() + Duration.ofHours(4),
        Instant.now() + Duration.ofHours(6)
    )

    @Test
    @DisplayName("Meeting search with both time bounds")
    fun searchMeetingsByBothBounds() {
        var response = meetingService.createMeeting(meetingDto1.mapToEntity())
        Assertions.assertNotNull(response)
        response = meetingService.createMeeting(meetingDto2.mapToEntity())
        Assertions.assertNotNull(response)
        response = meetingService.createMeeting(meetingDto3.mapToEntity())
        Assertions.assertNotNull(response)

        val expected = listOf(meetingDto1.name)

        val actual = meetingService.searchMeetingDog(bothBoundQuery).map{
            it.mapToDto().name
        }

        Assertions.assertTrue(actual.size == expected.size && actual.containsAll(expected))
    }

    val nameQuery = MeetingDogSearchQuery(
        "test1",
        null,
        null
    )

    @Test
    @DisplayName("Meeting search with both time bounds")
    fun searchMeetingsByName() {
        var response = meetingService.createMeeting(meetingDto1.mapToEntity())
        Assertions.assertNotNull(response)
        response = meetingService.createMeeting(meetingDto2.mapToEntity())
        Assertions.assertNotNull(response)
        response = meetingService.createMeeting(meetingDto3.mapToEntity())
        Assertions.assertNotNull(response)

        val expected = listOf(meetingDto1.name)

        val actual = meetingService.searchMeetingDog(nameQuery).map{
            it.mapToDto().name
        }

        Assertions.assertTrue(actual.size == expected.size && actual.containsAll(expected))
    }
}