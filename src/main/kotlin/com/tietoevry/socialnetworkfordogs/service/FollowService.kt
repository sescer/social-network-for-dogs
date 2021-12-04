package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Follow
import com.tietoevry.socialnetworkfordogs.entity.FollowId
import com.tietoevry.socialnetworkfordogs.repository.FollowRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class FollowService (
    private val followRepository: FollowRepository
) {

    fun createFollow(follow: Follow) : Follow {
        return followRepository.save(follow)
    }

    fun deleteFollow(followID: FollowId) {
        followRepository.delete(getFollow(followID))
    }

    fun getFollow(followID: FollowId) : Follow{
        return followRepository.findById(followID).get()
    }


    fun listOfFriends(from_id: Long) :List<Long>{
        return followRepository.findFriends(from_id)
    }

    fun listOfFollowers(from_id: Long) :List<Long>{
        return followRepository.findFollowers(from_id)
    }

    fun listOfFollowings(from_id: Long) :List<Long>{
        return followRepository.findFollowings(from_id)
    }

}