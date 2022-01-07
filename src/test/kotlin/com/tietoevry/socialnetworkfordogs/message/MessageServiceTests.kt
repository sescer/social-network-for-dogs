package com.tietoevry.socialnetworkfordogs.message

import com.tietoevry.socialnetworkfordogs.dto.DogDto
import com.tietoevry.socialnetworkfordogs.dto.MessageDto
import com.tietoevry.socialnetworkfordogs.entity.Breed
import com.tietoevry.socialnetworkfordogs.entity.Sex
import com.tietoevry.socialnetworkfordogs.entity.color.Color
import com.tietoevry.socialnetworkfordogs.mapper.mapToDto
import com.tietoevry.socialnetworkfordogs.mapper.mapToEntity
import com.tietoevry.socialnetworkfordogs.query.MessageSearchQuery
import com.tietoevry.socialnetworkfordogs.repository.DogRepository
import com.tietoevry.socialnetworkfordogs.repository.MessageRepository
import com.tietoevry.socialnetworkfordogs.service.DogService
import com.tietoevry.socialnetworkfordogs.service.MessageService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.Duration
import java.time.Instant

@Testcontainers
@DisplayName("Tests of message service")
@SpringBootTest
@ActiveProfiles("test")
class MessageServiceTests @Autowired constructor(
    private val messageRepository: MessageRepository,
    private val messageService: MessageService,
    private val dogService: DogService,
    private val dogRepository: DogRepository
) {

    private lateinit var testMessage12: MessageDto
    private lateinit var testMessage12Edited: MessageDto
    private lateinit var testMessage21: MessageDto

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

    private var dogId1 = 0L
    private var dogId2 = 0L


    @BeforeEach
    @Transactional
    fun createDogsAndSetupMessages() {
        dogId1 = dogService.createDog(dogDto1.mapToEntity())
        dogId2 = dogService.createDog(dogDto2.mapToEntity())

        testMessage12 = MessageDto (dogId1, dogId2, "Hello")
        testMessage12Edited = MessageDto (dogId1, dogId2, "Hello World")
        testMessage21 = MessageDto (dogId2, dogId1, "Hello!")
    }

    @AfterEach
    @Transactional
    fun deleteTestMessagesAndDogs() {
        messageRepository.deleteAll()
        dogRepository.deleteAll()
    }

    @Test
    @DisplayName("Send message. Expected - success")
    fun sendMessageTest() {
        val response = messageService.sendMessage(testMessage12.mapToEntity())
        Assertions.assertEquals(testMessage12, messageRepository.findById(response).get().mapToDto())
    }
    @Test
    @DisplayName("Send and edit a message. Expected - success")
    fun editMessageTest() {
        val messageId = messageService.sendMessage(testMessage12.mapToEntity())
        Assertions.assertEquals(testMessage12, messageRepository.findById(messageId).get().mapToDto())
        val response = messageService.updateMessage(testMessage12Edited.mapToEntity())
        Assertions.assertEquals(testMessage12Edited, response.mapToDto())
    }
    @Test
    @DisplayName("Get a message. Expected - success")
    fun getMessageTest() {
        val messageId = messageService.sendMessage(testMessage12.mapToEntity())
        Assertions.assertEquals(testMessage12, messageRepository.findById(messageId).get().mapToDto())
        val response = messageService.getMessage(messageId)
        Assertions.assertEquals(testMessage12, response.mapToDto())
    }
    @Test
    @DisplayName("Get a dialog. Expected - success")
    fun getDialogTest() {
        val messageId1 = messageService.sendMessage(testMessage12.mapToEntity())
        Assertions.assertEquals(testMessage12, messageRepository.findById(messageId1).get().mapToDto())

        val messageId2 = messageService.sendMessage(testMessage21.mapToEntity())
        Assertions.assertEquals(testMessage21, messageRepository.findById(messageId2).get().mapToDto())

        val response = messageService.getMessages(dogId1, dogId2)
        Assertions.assertTrue(response.size == 2
                && response[0].mapToDto() == testMessage12
                && response[1].mapToDto() == testMessage21)
    }

    @Test
    @DisplayName("Send and delete a message, then try to request it. Expected - exception is thrown")
    fun deleteMessageTest() {
        val messageId = messageService.sendMessage(testMessage12.mapToEntity())
        Assertions.assertEquals(testMessage12, messageRepository.findById(messageId).get().mapToDto())
        messageService.deleteMessage(messageId)
        Assertions.assertThrows(NoSuchElementException::class.java) {
            messageService.getMessage(messageId)
        }
    }


    private val testAllMessageSearchQuery = MessageSearchQuery(
        "Hello",
        Instant.now() - Duration.ofHours(4),
        Instant.now() + Duration.ofHours(4)
    )
    @Test
    @DisplayName("Send two messages and search a message by all parameters. Expected - success")
    fun searchMessageTest() {
        val messageId1 = messageService.sendMessage(testMessage12.mapToEntity())
        Assertions.assertEquals(testMessage12, messageRepository.findById(messageId1).get().mapToDto())

        val messageId2 = messageService.sendMessage(testMessage21.mapToEntity())
        Assertions.assertEquals(testMessage21, messageRepository.findById(messageId2).get().mapToDto())

        val messageList = messageService.searchMessage(dogId1, testAllMessageSearchQuery)
        Assertions.assertTrue(messageList.size == 2
                && messageList[0].mapToDto() == testMessage12
                && messageList[1].mapToDto() == testMessage21)
    }

    private val testContentMessageSearchQuery = MessageSearchQuery(
        "Hel",
        null,
        null
    )
    @Test
    @DisplayName("Send two messages and search a message by content. Expected - success")
    fun searchMessageByContentTest() {
        val messageId1 = messageService.sendMessage(testMessage12.mapToEntity())
        Assertions.assertEquals(testMessage12, messageRepository.findById(messageId1).get().mapToDto())

        val messageId2 = messageService.sendMessage(testMessage21.mapToEntity())
        Assertions.assertEquals(testMessage21, messageRepository.findById(messageId2).get().mapToDto())

        val messageList = messageService.searchMessage(dogId1, testContentMessageSearchQuery)
        Assertions.assertTrue(messageList.size == 2
                && messageList[0].mapToDto() == testMessage12
                && messageList[1].mapToDto() == testMessage21)
    }

    private val testBothBoundsMessageSearchQuery = MessageSearchQuery(
        null,
        Instant.now() - Duration.ofHours(1),
        Instant.now() + Duration.ofHours(1)
    )
    @Test
    @DisplayName("Send two messages and search a message by both bounds. Expected - success")
    fun searchMessageByBothBoundsTest() {
        val messageId1 = messageService.sendMessage(testMessage12.mapToEntity())
        Assertions.assertEquals(testMessage12, messageRepository.findById(messageId1).get().mapToDto())

        val messageId2 = messageService.sendMessage(testMessage21.mapToEntity())
        Assertions.assertEquals(testMessage21, messageRepository.findById(messageId2).get().mapToDto())

        val messageList = messageService.searchMessage(dogId1, testBothBoundsMessageSearchQuery)
        Assertions.assertTrue(messageList.size == 2
                && messageList[0].mapToDto() == testMessage12
                && messageList[1].mapToDto() == testMessage21)
    }

    private val testUpperBoundMessageSearchQuery = MessageSearchQuery(
        null,
        null,
        Instant.now() + Duration.ofHours(1)
    )
    @Test
    @DisplayName("Send two messages and search a message by upper bound. Expected - success")
    fun searchMessageByUpperBoundTest() {
        val messageId1 = messageService.sendMessage(testMessage12.mapToEntity())
        Assertions.assertEquals(testMessage12, messageRepository.findById(messageId1).get().mapToDto())
        val messageId2 = messageService.sendMessage(testMessage21.mapToEntity())
        Assertions.assertEquals(testMessage21, messageRepository.findById(messageId2).get().mapToDto())
        val messageList = messageService.searchMessage(dogId1, testUpperBoundMessageSearchQuery)
        Assertions.assertTrue(messageList.size == 2
                && messageList[0].mapToDto() == testMessage12
                && messageList[1].mapToDto() == testMessage21)
    }
    private val testLowerBoundMessageSearchQuery = MessageSearchQuery(
        null,
        Instant.now() - Duration.ofHours(1),
        null
    )
    @Test
    @DisplayName("Send two messages and search a message by lower bound. Expected - success")
    fun searchMessageByLowerBoundTest() {
        val messageId1 = messageService.sendMessage(testMessage12.mapToEntity())
        Assertions.assertEquals(testMessage12, messageRepository.findById(messageId1).get().mapToDto())
        val messageId2 = messageService.sendMessage(testMessage21.mapToEntity())
        Assertions.assertEquals(testMessage21, messageRepository.findById(messageId2).get().mapToDto())
        val messageList = messageService.searchMessage(dogId1, testLowerBoundMessageSearchQuery)
        Assertions.assertTrue(messageList.size == 2
                && messageList[0].mapToDto() == testMessage12
                && messageList[1].mapToDto() == testMessage21)
    }
}