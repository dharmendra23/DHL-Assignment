package com.example.user_service.model

import jakarta.persistence.*

@Entity
@Table(name = "users")   // ✅ IMPORTANT
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val email: String
)
