package com.contentflow.contentflow.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    @NotEmpty(message = "name cannot be blank")
    String name;
    @Email(message = "email is invalid")
    String email;
    @NotEmpty
    @Size(min = 4, max = 10, message = "password must be minimum 4 characters and maximum 10 characters long")
    String password;
    @NotEmpty
    String about;

}
