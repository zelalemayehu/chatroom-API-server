package com.chatroom.whatsappapi.chatroom.controller;

import com.chatroom.whatsappapi.chatroom.service.dto.UserDTO;
import com.chatroom.whatsappapi.chatroom.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

}
