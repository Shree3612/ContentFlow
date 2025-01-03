package com.contentflow.contentflow.Converter;


import com.contentflow.contentflow.dto.request.UserRequest;
import com.contentflow.contentflow.dto.response.UserResponse;
import com.contentflow.contentflow.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserConverter {
//    static ModelMapper modelMapper;

    public static User userDtoToUserEntity(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setAbout(userRequest.getAbout());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public static UserResponse userEntityToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

}
