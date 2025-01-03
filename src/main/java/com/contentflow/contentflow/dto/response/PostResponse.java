package com.contentflow.contentflow.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    String title;
    String content;
    String imageName;
    Date addedDate;
    CategoryResponse categoryResponse;
    UserResponse userResponse;
    List<CommentResponse> commentResponse;

    //lombok not working therefore created getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public CategoryResponse getCategoryResponse() {
        return categoryResponse;
    }

    public void setCategoryResponse(CategoryResponse categoryResponse) {
        this.categoryResponse = categoryResponse;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public List<CommentResponse> getCommentResponse() {
        return commentResponse;
    }

    public void setCommentResponse(List<CommentResponse> commentResponse) {
        this.commentResponse = commentResponse;
    }
}
