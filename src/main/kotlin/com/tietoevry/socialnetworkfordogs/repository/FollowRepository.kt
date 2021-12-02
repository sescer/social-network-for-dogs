package com.tietoevry.socialnetworkfordogs.repository

import com.tietoevry.socialnetworkfordogs.entity.Follow
import com.tietoevry.socialnetworkfordogs.entity.FollowId
import org.springframework.data.repository.CrudRepository

interface FollowRepository : CrudRepository<Follow, FollowId>