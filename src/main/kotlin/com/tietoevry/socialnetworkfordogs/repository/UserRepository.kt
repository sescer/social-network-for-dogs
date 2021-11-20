package com.tietoevry.socialnetworkfordogs.repository

import com.tietoevry.socialnetworkfordogs.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>