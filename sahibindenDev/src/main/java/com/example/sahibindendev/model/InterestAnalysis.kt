package com.example.sahibindendev.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class InterestAnalysis @JvmOverloads constructor(

        @Id
        @Column(name = "actor_id")
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        @Column(unique = true)
        val userId:String,

        val possibleInterestedCategory:CategoryType,
        val possibleBudget:String

)
enum class CategoryType {
    SHOPPING, REAL_ESTATE, VEHICLE
}