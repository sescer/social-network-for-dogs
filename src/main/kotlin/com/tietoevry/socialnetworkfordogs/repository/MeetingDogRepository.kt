package com.tietoevry.socialnetworkfordogs.repository

import com.tietoevry.socialnetworkfordogs.entity.MeetingDog
import com.tietoevry.socialnetworkfordogs.entity.MeetingDogId
import org.springframework.data.repository.CrudRepository

interface MeetingDogRepository : CrudRepository<MeetingDog, MeetingDogId>