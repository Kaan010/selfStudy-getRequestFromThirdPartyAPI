package com.example.sahibindendev.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class AccessLog  @JvmOverloads constructor(
        @Id
        val id: String,
        val usersId: String,
        val endPoint:String

)