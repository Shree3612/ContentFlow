package com.contentflow.contentflow.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {

    @NotEmpty
    @Size(min = 4, max = 15, message = "title must be minimum 4 characters and maximum 15 characters long")
    String title;
    @Size(min = 4, max = 1000, message = "content must be minimum 4 characters and maximum 1000 characters long")
    String content;
}
