package com.tietoevry.socialnetworkfordogs.entity

import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Follow {
    @EmbeddedId
    val followID : FollowId = FollowId(0, 0)
}