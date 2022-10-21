package com.example.demo.resources;

import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity addUser(final @RequestBody(required = true) UserCreateDTO userCreateDTO) {

        final Optional<UserDTO> userDTO = userService.addNewUser(userCreateDTO);
        if (userDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User creation failed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO.get());
    }

    @PutMapping
    public ResponseEntity updateUser(final @RequestBody UserDTO userDTO) {

        final Optional<UserDTO> updatedUser = userService.updateExistingUser(userDTO);
        if (updatedUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User update failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(final @PathVariable(required = true) Integer id) {

        final Optional<UserDTO> userById = userService.getUserById(id);
        if (userById.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("User with given id not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userById);
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {

        final List<UserDTO> users = userService.getUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Users not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsers(final @PathVariable(required = true) Integer id) {

        final boolean isDeleted = userService.deleteUserById(id);
        if (!isDeleted) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to delete user as ticket with given user id exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body("User successfully deleted");
    }
}
