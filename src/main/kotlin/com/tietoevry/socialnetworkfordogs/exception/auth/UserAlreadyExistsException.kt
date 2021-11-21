package com.tietoevry.socialnetworkfordogs.exception.auth

/**
 * Класс исключение. Выбрасывается в случае, если пользователь с такой почтой или логином уже существует
 */
class UserAlreadyExistsException(override val message: String): RuntimeException()