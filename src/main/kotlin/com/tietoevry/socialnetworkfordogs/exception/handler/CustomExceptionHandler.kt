package com.tietoevry.socialnetworkfordogs.exception.handler

import com.tietoevry.socialnetworkfordogs.dto.StatusMessageDto
import com.tietoevry.socialnetworkfordogs.exception.auth.UserAlreadyExistsException
import com.tietoevry.socialnetworkfordogs.exception.auth.UserNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Перехватчик exception-ов. Нужен для стандартизированного ответа в случае вылета exception.
 */
@ControllerAdvice
class CustomExceptionHandler : ResponseEntityExceptionHandler() {

    companion object {
        private const val UNEXPECTED_ERROR_MESSAGE = "Возникла непредвиденная ошибка"
    }

    /**
     * Функция перехвата exception "пользователь не найден"
     */
    @ExceptionHandler(UserNotFoundException::class)
    protected fun handleUserNotFoundException(exception: RuntimeException, request: WebRequest): ResponseEntity<*> {
        return handleExceptionInternal(
            exception,
            StatusMessageDto(
                userMessage = exception.message ?: UNEXPECTED_ERROR_MESSAGE,
                internalMessage = exception.stackTraceToString()
            ),
            HttpHeaders(),
            HttpStatus.NOT_FOUND,
            request
        )
    }

    /**
     * Функция перехвата runtime exception
     */
    @ExceptionHandler(UserAlreadyExistsException::class)
    protected fun handleUserAlreadyExistsException(exception: RuntimeException, request: WebRequest): ResponseEntity<*> {
        return handleExceptionInternal(
            exception, StatusMessageDto(
                userMessage = exception.message ?: UNEXPECTED_ERROR_MESSAGE,
                internalMessage = exception.stackTraceToString()
            ),
            HttpHeaders(),
            HttpStatus.BAD_REQUEST,
            request
        )
    }
}
