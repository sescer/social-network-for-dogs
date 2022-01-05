package com.tietoevry.socialnetworkfordogs.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
/**
 * Class represents user entity
 */
@Entity
@Table(name = "user_table")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val login: String,

    val password: String,

    val firstname: String,

    val lastname: String,

    val mail: String,

    val phoneNumber: String,
) {
    constructor() : this(
        0,
        "",
        "",
        "",
        "",
        "",
        "",
    )
}
