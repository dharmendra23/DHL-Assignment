package com.example.user_service.repository

import com.example.user_service.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
