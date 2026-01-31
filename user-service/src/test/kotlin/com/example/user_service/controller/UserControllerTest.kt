package com.example.user_service.controller

import com.example.user_service.dto.UserRequest
import com.example.user_service.model.User
import com.example.user_service.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var userService: UserService

    @Autowired
    lateinit var objectMapper: ObjectMapper

    // ---------------- CREATE USER ----------------

    @Test
    fun `should create user`() {
        val request = UserRequest("Dharmendra", "dharmendra@test.com")
        val savedUser = User(1, "Dharmendra", "dharmendra@test.com")

        `when`(userService.createUser(any(User::class.java)))
            .thenReturn(savedUser)

        mockMvc.perform(
            post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Dharmendra"))
            .andExpect(jsonPath("$.email").value("dharmendra@test.com"))
    }

    // ---------------- GET ALL USERS ----------------

    @Test
    fun `should return all users`() {
        val users = listOf(
            User(1, "User1", "u1@test.com"),
            User(2, "User2", "u2@test.com")
        )

        `when`(userService.getAllUsers()).thenReturn(users)

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
    }

    // ---------------- GET USER BY ID ----------------

    @Test
    fun `should return user by id`() {
        val user = User(1, "User1", "u1@test.com")

        `when`(userService.getUserById(1)).thenReturn(user)

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("User1"))
    }

    // ---------------- UPDATE USER ----------------

    @Test
    fun `should update user`() {
        val request = UserRequest("Updated", "updated@test.com")
        val updatedUser = User(1, "Updated", "updated@test.com")

        `when`(userService.updateUser(anyLong(), any(User::class.java)))
            .thenReturn(updatedUser)

        mockMvc.perform(
            put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("Updated"))
            .andExpect(jsonPath("$.email").value("updated@test.com"))
    }

    // ---------------- DELETE USER ----------------

    @Test
    fun `should delete user`() {
        mockMvc.perform(delete("/api/users/1"))
            .andExpect(status().isNoContent)
    }

    // ---------------- VALIDATION FAILURE ----------------

    @Test
    fun `should fail validation when name is blank`() {
        val request = UserRequest("", "invalid")

        mockMvc.perform(
            post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isBadRequest)
    }
}
