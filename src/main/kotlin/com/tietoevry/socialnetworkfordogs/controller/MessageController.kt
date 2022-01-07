package com.tietoevry.socialnetworkfordogs.controller

import com.tietoevry.socialnetworkfordogs.entity.Message
import com.tietoevry.socialnetworkfordogs.query.MessageSearchQuery
import com.tietoevry.socialnetworkfordogs.service.MessageService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/messages")
class MessageController(
    private val service: MessageService,
) {
    @PostMapping("/create")
    fun sendMessage(@RequestBody message: Message) {
        service.sendMessage(message)
    }

    @PostMapping("/update")
    fun updateMessage(@RequestBody message: Message) {
        service.updateMessage(message)
    }

    @DeleteMapping("/")
    fun deleteMessage(@RequestBody id: Long) {
        service.deleteMessage(id)
    }

    @GetMapping("/{idFrom}")
    fun getMessages(@PathVariable idFrom: Long,
                    @RequestBody idTo: Long): List<Message> {
        return service.getMessages(idFrom, idTo)
    }

   @PostMapping("/{idFrom}/search")
   fun findMessage(@PathVariable idFrom: Long,
                   @RequestBody content: MessageSearchQuery): List<Message> {
       return service.searchMessage(idFrom, content)
   }
}