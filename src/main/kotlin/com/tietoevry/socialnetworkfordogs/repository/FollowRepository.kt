package com.tietoevry.socialnetworkfordogs.repository

import com.tietoevry.socialnetworkfordogs.entity.Follow
import com.tietoevry.socialnetworkfordogs.entity.FollowId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface FollowRepository : CrudRepository<Follow, FollowId>{

    @Query(value = "select to_id from (select a.to_id from Follow a where a.from_id = (:from_id)) as followed inner join (select b.from_id from Follow b where b.to_id = (:from_id)) as follows on followed.to_id = follows.from_id", nativeQuery = true)
    fun findFriends(from_id: Long) :List<Long>

    @Query(value = "select * from (select a.from_id from Follow a where a.to_id = (:from_id)) as followed  except (select b.to_id from Follow b where b.from_id = (:from_id))", nativeQuery = true)
    fun findFollowers(from_id: Long) :List<Long>

    @Query(value = "select * from (select a.to_id from Follow a where a.from_id = (:from_id)) as followed  except (select b.from_id from Follow b where b.to_id = (:from_id))", nativeQuery = true)
    fun findFollowings(from_id: Long) :List<Long>
}