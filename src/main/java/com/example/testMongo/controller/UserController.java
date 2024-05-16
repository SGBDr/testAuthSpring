package com.example.testMongo.controller;

import com.example.testMongo.dto.AuthDto;
import com.example.testMongo.dto.CredentialDto;
import com.example.testMongo.dto.UserDto;
import com.example.testMongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "auth")
    private ResponseEntity<AuthDto> auth(@RequestBody CredentialDto credentialDto) {
        return ResponseEntity.status(202).body(userService.auth(credentialDto));
    }

    @PostMapping(path = "register")
    private ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return ResponseEntity.status(201).body(userService.createUser(userDto));
    }

    @GetMapping("user/info")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            // User is authenticated, retrieve user information
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            // Do something with the user information
            return "Welcome, " + name + " (" + email + ")!";
        } else {
            // User is not authenticated, handle accordingly
            return "User not authenticated";
        }
    }
}
