package com.tietoevry.socialnetworkfordogs.entity

import java.io.Serializable


data class FollowId(
    val fromId: Long = 0,
    val toId: Long = 0
): Serializable
