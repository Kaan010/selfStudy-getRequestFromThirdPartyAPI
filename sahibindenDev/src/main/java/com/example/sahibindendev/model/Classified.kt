package com.example.sahibindendev.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Classified @JvmOverloads constructor(
        @Id //PRIMARY KEY
        @Column(name = "classified_id")
        val id: String,
        val usersId: String,
        val currency:String,
        val price:String,
        val category:String

)