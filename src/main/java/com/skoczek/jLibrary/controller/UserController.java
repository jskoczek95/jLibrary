package com.skoczek.jLibrary.controller;

import com.skoczek.jLibrary.dto.UserDTO;
import com.skoczek.jLibrary.dto.UserRegistrationDTO;
import com.skoczek.jLibrary.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        UserDTO registeredUser = userService.registerUser(userRegistrationDTO);

        return ResponseEntity.status(201).body(registeredUser);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO userDTO = userService.findById(id);

        return ResponseEntity.ok(userDTO);
    }
}
