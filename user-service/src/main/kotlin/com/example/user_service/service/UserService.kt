package com.example.user_service.service

import com.example.user_service.model.User
import com.example.user_service.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(user: User): User =
        userRepository.save(user)

    fun getAllUsers(): List<User> =
        userRepository.findAll()

    fun getUserById(id: Long): User =
        userRepository.findById(id)
            .orElseThrow { RuntimeException("User not found") }

    fun updateUser(id: Long, updatedUser: User): User {
        val user = getUserById(id)
        return userRepository.save(
            user.copy(
                name = updatedUser.name,
                email = updatedUser.email
            )
        )
    }

    fun deleteUser(id: Long) =
        userRepository.deleteById(id)
}
