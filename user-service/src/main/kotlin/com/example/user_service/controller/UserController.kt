package com.example.user_service.controller

import com.example.user_service.dto.UserRequest
import com.example.user_service.model.User
import com.example.user_service.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:5173"])
@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(
        @Valid @RequestBody request: UserRequest
    ): ResponseEntity<User> {

        val user = User(
            name = request.name,
            email = request.email
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.createUser(user))
    }

    @GetMapping
    fun getAllUsers(): List<User> =
        userService.getAllUsers()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): User =
        userService.getUserById(id)

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @Valid @RequestBody request: UserRequest
    ): User {

        val updatedUser = User(
            name = request.name,
            email = request.email
        )

        return userService.updateUser(id, updatedUser)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }
}
