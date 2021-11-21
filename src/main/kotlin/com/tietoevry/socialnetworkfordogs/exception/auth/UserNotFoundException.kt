package com.tietoevry.socialnetworkfordogs.exception.auth

/**
 * Класс исключение. Выбрасывается в случае, если пользователь не найден в базе
 */
class UserNotFoundException(message: String) : RuntimeException(message)
