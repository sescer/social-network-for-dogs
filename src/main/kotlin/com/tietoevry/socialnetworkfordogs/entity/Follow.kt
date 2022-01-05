package com.tietoevry.socialnetworkfordogs.entity

import javax.persistence.*
/**
 * Class represents entity followers, followings & friends
 */
@Entity
@IdClass(FollowId::class)
@Table(name="follow")
data class Follow (
    @Id
    val fromId: Long = 0,

    @Id
    val toId: Long = 0,
)