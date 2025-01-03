package com.contentflow.contentflow.controller;


import com.contentflow.contentflow.dto.request.UserRequest;
import com.contentflow.contentflow.dto.response.UserResponse;
import com.contentflow.contentflow.exception.UserNotFoundException;
import com.contentflow.contentflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(@Valid @RequestBody UserRequest userRequest) {
            UserResponse response = userService.createUser(userRequest);
            return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable("userId") int userId) {
        try{
            return new ResponseEntity(userService.updateUser(userRequest, userId), HttpStatus.CREATED);
        }
        catch(UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity getUserById(@PathVariable("userId") int userId) {
        try{
            return new ResponseEntity(userService.getUserById(userId), HttpStatus.OK);
        }
        catch(UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllUser() {
        List<UserResponse> responses = userService.getAllUser();
        return new ResponseEntity<List<UserResponse>>(responses, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Integer userId) {
        try{
            return new ResponseEntity(userService.deleteUser(userId), HttpStatus.OK);
        }
        catch(UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
