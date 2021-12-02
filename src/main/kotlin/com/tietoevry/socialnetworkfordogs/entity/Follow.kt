package com.tietoevry.socialnetworkfordogs.entity

import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(FollowId::class)
data class Follow (
    @Id
    val from: Long = 0,
    @Id
    val to: Long = 0,
)