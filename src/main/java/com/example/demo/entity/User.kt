package com.example.demo.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
class User(
        @Id @GeneratedValue var id: Long? = null,
        var login: String,
        var password: String,
        var firstname: String,
        var lastname: String,
        var mail: String,
        var phoneNumber: String,
)