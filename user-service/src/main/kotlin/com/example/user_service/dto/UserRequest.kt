package com.example.user_service.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserRequest(

    @field:NotBlank(message = "Name must not be blank")
    @field:Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    val name: String,

    @field:NotBlank(message = "Email must not be blank")
    @field:Email(message = "Invalid email format")
    val email: String
)
