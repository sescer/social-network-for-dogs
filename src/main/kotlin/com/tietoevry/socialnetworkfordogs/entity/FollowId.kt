package com.tietoevry.socialnetworkfordogs.entity

import javax.persistence.Embeddable
import javax.persistence.Id


@Embeddable
data class FollowId (
    @Id
    val from: Long = 0,
    @Id
    val to: Long = 0
)