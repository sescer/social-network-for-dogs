package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Follow
import com.tietoevry.socialnetworkfordogs.entity.FollowId
import com.tietoevry.socialnetworkfordogs.repository.FollowRepository
import org.springframework.stereotype.Service

/**
 * Class represents followers, followings and friends of dog
 */
@Service
class FollowService (
    private val followRepository: FollowRepository
) {
    /**
     * Method for creating friend request to dog
     *
     * @param follow - from user's dog to anoter user's dog
     * @return - follow (from user's dog to anoter user's dog)
     */
    fun createFollow(follow: Follow) : Follow {
        return followRepository.save(follow)
    }
    /**
     * Method for deleting friend request to dog
     *
     * @param followID - from user's dog to anoter user's dog
     */
    fun deleteFollow(followID: FollowId) {
        followRepository.delete(getFollow(followID))
    }
    /**
     * Method for getting friend requests to dog by id
     *
     * @param followID - from user's dog to anoter user's dog
     * @return - follow (from user's dog to anoter user's dog)
     */
    fun getFollow(followID: FollowId) : Follow{
        return followRepository.findById(followID).get()
    }

    /**
     * Method for getting dog's friends
     *
     * @param from_id - user's dog id
     * @return - list of friends
     */
    fun listOfFriends(from_id: Long) :List<Long>{
        return followRepository.findFriends(from_id)
    }
    /**
     * Method for getting dog's followers
     *
     * @param from_id - user's dog id
     * @return - list of followers
     */
    fun listOfFollowers(from_id: Long) :List<Long>{
        return followRepository.findFollowers(from_id)
    }
    /**
     * Method for getting dog's followings
     *
     * @param from_id - user's dog id
     * @return - list of followings
     */
    fun listOfFollowings(from_id: Long) :List<Long>{
        return followRepository.findFollowings(from_id)
    }

}