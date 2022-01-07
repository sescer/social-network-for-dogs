package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Message
import com.tietoevry.socialnetworkfordogs.query.MessageSearchQuery
import com.tietoevry.socialnetworkfordogs.repository.MessageRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Locale

/**
 * Class represents message service
 */
@Service
class MessageService (
    private val repository: MessageRepository,
){
    /**
     * Method for sending message
     *
     * @param message - message to send from dog to another dog
     * @return - id of sent message
     */
    fun sendMessage(message: Message): Long {
        return repository.save(message).id
    }
    /**
     * Method for editing message
     *
     * @param message - message to edit from dog to another dog
     * @return - edited message
     */
    fun updateMessage(message: Message) : Message {
        return repository.save(message)
    }
    /**
     * Method for deleting message
     *
     * @param id - message id to delete
     */
    fun deleteMessage(id: Long) {
        repository.delete(getMessage(id))
    }
    /**
     * Method for getting message by id
     *
     * @param id - message id to get
     * @return - message
     */
    fun getMessage(id: Long): Message {
        return repository.findById(id).get()
    }
    /**
     * Method for getting messages from one dog(dialog)
     *
     * @param idFrom - dog's id from
     * @param idTo - dog's id to
     * @return - list of messages between idFrom and idTo
     */
    fun getMessages(idFrom: Long, idTo: Long): List<Message> {
        return repository.getMessages(idFrom, idTo)
    }

    /**
     * Method for searching messages
     *
     * @param idFrom - dog's id
     * @param messageSearchQuery - searching query(by content/date)
     * @return - list of found messages
     */
    fun searchMessage(idFrom:Long, messageSearchQuery: MessageSearchQuery): List<Message> {
        return repository.findAll(
            isInDateRange(messageSearchQuery.startDate, messageSearchQuery.endDate)
                .and(containsContent(messageSearchQuery.content)
                    .and(containsDogFrom(idFrom))
                    .or(containsDogTo(idFrom))
                )
        )
    }
    /**
     * Method for creating query to db for searching messages by dog
     *
     * @param dogFrom - dog's id to search
     * @return - specification of found messages
     */
    fun containsDogFrom(dogFrom: Long?): Specification<Message> {
        return Specification<Message> { root, query, builder ->
            if (dogFrom != null) {
                builder.equal(builder.toLong(root.get("dogFrom")), "${dogFrom}")
            } else {
                null
            }
        }
    }
    /**
     * Method for creating query to db for searching messages by dog
     *
     * @param dogFrom - dog's id to search
     * @return - specification of found messages
     */
    fun containsDogTo(dogFrom: Long?): Specification<Message> {
        return Specification<Message> { root, query, builder ->
            if (dogFrom != null) {
                builder.equal(builder.toLong(root.get("dogTo")), "${dogFrom}")
            } else {
                null
            }
        }
    }
    /**
     * Method for creating query to db for searching messages by content
     *
     * @param content - message to search
     * @return - specification of found messages
     */
    fun containsContent(content: String?): Specification<Message> {
        return Specification<Message> { root, query, builder ->
            if (content != null && content.isNotBlank()) {
                builder.like(builder.lower(root.get("content")), "%${content.lowercase(Locale.getDefault())}%")
            } else {
                null
            }
        }
    }
    /**
     * Method for creating query to db for searching messages by date's interval
     *
     * @param startDate - start(interval) date of message to search
     * @param endDate - end(interval) date of message to search
     * @return - specification of found messages
     */
    fun isInDateRange(startDate: Instant?, endDate: Instant?): Specification<Message> {
        return Specification<Message> { root, _, builder ->
            if (startDate == null && endDate == null)
                null
            else if (startDate == null)
                builder.lessThanOrEqualTo(root.get("creationDate"), endDate!!)
            else if (endDate == null)
                builder.greaterThanOrEqualTo(root.get("creationDate"), startDate)
            else
                builder.between(root.get("creationDate"), startDate, endDate)
        }
    }

}