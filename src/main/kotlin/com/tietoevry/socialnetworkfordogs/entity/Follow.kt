package com.tietoevry.socialnetworkfordogs.entity

import javax.persistence.*

@Entity
@IdClass(FollowId::class)
@Table(name="follow")
data class Follow (
    @Id
    val from_id: Long = 0 ,
    @Id
    val to_id: Long = 0 ,
)