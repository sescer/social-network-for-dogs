package com.tietoevry.socialnetworkfordogs.dto

/**
 * DTO статуса запроса
 */
data class StatusMessageDto(
    /**
     * Внутреннее сообщение, предназначенное для сервера. Пользователю не отображается
     */
    private val internalMessage: String,
    /**
     * Сообщение для пользователя
     */
    private val userMessage: String,
)
