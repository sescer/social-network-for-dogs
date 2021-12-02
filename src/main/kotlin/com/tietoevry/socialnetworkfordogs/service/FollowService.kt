package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Follow
import com.tietoevry.socialnetworkfordogs.entity.FollowId
import com.tietoevry.socialnetworkfordogs.repository.FollowRepository
import org.springframework.stereotype.Service

@Service
class FollowService (
    private val followRepository: FollowRepository
) {

    fun createFollow(follow: Follow) : Follow {
        return followRepository.save(follow)
    }

    fun updateFollow(follow: Follow) : Follow {
        return followRepository.save(follow)
    }

    fun deleteFollow(followID: FollowId) {
        followRepository.delete(getFollow(followID))
    }

    fun getFollow(followID: FollowId) : Follow{
        return followRepository.findById(followID).get()
    }

}