package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Message
import com.tietoevry.socialnetworkfordogs.query.MessageSearchQuery
import com.tietoevry.socialnetworkfordogs.repository.MessageRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageService (
    private val repository: MessageRepository,
){
    fun sendMessage(message: Message): Long? {
        return repository.save(message).id
    }

    fun updateMessage(message: Message) : Message {
        return repository.save(message)
    }

    fun deleteMessage(id: Long) {
        repository.delete(getMessage(id))
    }

    fun getMessage(id: Long): Message {
        return repository.findById(id).get()
    }

    fun getMessages(idFrom: Long, idTo: Long): List<Message> {
        return repository.getMessages(idFrom, idTo)
    }


    fun searchMessage(idFrom:Long, messageSearchQuery: MessageSearchQuery): List<Message> {
        return repository.findAll(
            isInDateRange(messageSearchQuery.startDate, messageSearchQuery.endDate)
                .and(containsContent(messageSearchQuery.content)
                    .and(containsDogFrom(idFrom))
                )
        )
    }

    fun containsDogFrom(dogFrom: Long?): Specification<Message> {
        return Specification<Message> { root, query, builder ->
            if (dogFrom != null) {
                builder.equal(builder.toLong(root.get("dogFrom")), "${dogFrom}")
            } else {
                null
            }
        }
    }

    fun containsContent(content: String?): Specification<Message> {
        return Specification<Message> { root, query, builder ->
            if (content != null && content.isNotBlank()) {
                builder.like(builder.lower(root.get("content")), "%${content.lowercase(Locale.getDefault())}%")
            } else {
                null
            }
        }
    }

    fun isInDateRange(startDate: Date?, endDate: Date?): Specification<Message> {
        return Specification<Message> { root, query, builder ->
            if (startDate == null && endDate == null)
                null
            else if (startDate == null)
                builder.lessThanOrEqualTo(root.get("creationDate"), endDate!!)
            else if (endDate == null)
                builder.greaterThanOrEqualTo(root.get("creationDate "), startDate)
            else
                builder.between(root.get("creationDate"), startDate, endDate)
        }
    }

}