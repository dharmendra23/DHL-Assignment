package com.example.user_service.service

import com.example.user_service.model.User
import com.example.user_service.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @InjectMocks
    lateinit var userService: UserService

    @Test
    fun `should create user successfully`() {
        val user = User(name = "Dharmendra", email = "dharmendra@test.com")

        `when`(userRepository.save(user)).thenReturn(user)

        val savedUser = userService.createUser(user)

        assertEquals("Dharmendra", savedUser.name)
        assertEquals("dharmendra@test.com", savedUser.email)
        verify(userRepository, times(1)).save(user)
    }

    @Test
    fun `should return all users`() {
        val users = listOf(
            User(1, "User1", "u1@test.com"),
            User(2, "User2", "u2@test.com")
        )

        `when`(userRepository.findAll()).thenReturn(users)

        val result = userService.getAllUsers()

        assertEquals(2, result.size)
        verify(userRepository).findAll()
    }

    @Test
    fun `should return user by id`() {
        val user = User(1, "User1", "u1@test.com")

        `when`(userRepository.findById(1)).thenReturn(Optional.of(user))

        val result = userService.getUserById(1)

        assertEquals("User1", result.name)
    }

    @Test
    fun `should throw exception when user not found`() {
        `when`(userRepository.findById(1)).thenReturn(Optional.empty())

        val exception = assertThrows(RuntimeException::class.java) {
            userService.getUserById(1)
        }

        assertEquals("User not found", exception.message)
    }

    @Test
    fun `should update user successfully`() {
        val existingUser = User(1, "Old Name", "old@test.com")
        val updatedUser = User(name = "New Name", email = "new@test.com")

        `when`(userRepository.findById(1)).thenReturn(Optional.of(existingUser))
        `when`(userRepository.save(any(User::class.java))).thenReturn(
            existingUser.copy(
                name = updatedUser.name,
                email = updatedUser.email
            )
        )

        val result = userService.updateUser(1, updatedUser)

        assertEquals("New Name", result.name)
        assertEquals("new@test.com", result.email)
    }

    @Test
    fun `should delete user by id`() {
        doNothing().`when`(userRepository).deleteById(1)

        userService.deleteUser(1)

        verify(userRepository, times(1)).deleteById(1)
    }
}
