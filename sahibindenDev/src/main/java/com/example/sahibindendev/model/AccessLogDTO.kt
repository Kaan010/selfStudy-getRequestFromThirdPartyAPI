package com.example.sahibindendev.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class AccessLogDTO  @JvmOverloads constructor(
        @Id
        val id: Long,
        val usersId: Long,
        val endPoint:String

)