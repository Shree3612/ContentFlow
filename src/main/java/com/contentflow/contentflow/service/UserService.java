package com.contentflow.contentflow.service;


import com.contentflow.contentflow.Converter.UserConverter;
import com.contentflow.contentflow.dto.request.UserRequest;
import com.contentflow.contentflow.dto.response.UserResponse;
import com.contentflow.contentflow.entities.User;
import com.contentflow.contentflow.exception.UserNotFoundException;
import com.contentflow.contentflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest) {
        User user = UserConverter.userDtoToUserEntity(userRequest);
        User savedUser = userRepository.save(user);
        return UserConverter.userEntityToUserResponse(savedUser);
    }

    public UserResponse updateUser(UserRequest userRequest, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with this ID not present"));

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setAbout(userRequest.getAbout());
        user.setPassword(userRequest.getPassword());
        User updatedUser = userRepository.save(user);
        return UserConverter.userEntityToUserResponse(updatedUser);
    }

    public UserResponse getUserById(Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User with this ID not present"));

        UserResponse response = UserConverter.userEntityToUserResponse(user);
        return response;
    }

    public List<UserResponse> getAllUser(){
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(UserConverter.userEntityToUserResponse(user));
        }
        return responses;
    }

    public String deleteUser(Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("Player with this ID not present"));

        userRepository.delete(user);
        return "user deleted successfully";
    }

}
