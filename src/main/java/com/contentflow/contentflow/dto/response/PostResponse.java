package com.contentflow.contentflow.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    String title;
    String content;
    String imageName;
    Date addedDate;
    CategoryResponse categoryResponse;
    UserResponse userResponse;
    List<CommentResponse> commentResponse;

}
