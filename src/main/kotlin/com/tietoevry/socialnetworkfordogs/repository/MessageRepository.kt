package com.tietoevry.socialnetworkfordogs.repository

import com.tietoevry.socialnetworkfordogs.entity.Message
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, Long>, JpaSpecificationExecutor<Message> {
    @Query(value="select * from messages m where m.dog_from = (:idFrom) and m.dog_to = (:idTo)", nativeQuery = true)
    fun getMessages(idFrom: Long, idTo: Long): List<Message>

}
