package com.contentflow.contentflow.Converter;

import com.contentflow.contentflow.dto.request.PostRequest;
import com.contentflow.contentflow.dto.response.CommentResponse;
import com.contentflow.contentflow.dto.response.PostResponse;
import com.contentflow.contentflow.entities.Comment;
import com.contentflow.contentflow.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostConverter {

    public static Post postDtoToPostEntity(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return post;
    }

    public static PostResponse postEntityToPostResponse(Post post){
        PostResponse postResponse = new PostResponse();
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setImageName(post.getImageName());
        postResponse.setAddedDate(post.getAddedDate());
        postResponse.setCategoryResponse(CategoryConverter.categoryEntityToCategoryResponse(post.getCategory()));
        postResponse.setUserResponse(UserConverter.userEntityToUserResponse(post.getUser()));

        // Map comments to CommentResponse so that we can see comments on a post
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            CommentResponse commentResponse = CommentConverter.commentEntityToCommentResponse(comment);
            commentResponses.add(commentResponse);
        }
        postResponse.setCommentResponse(commentResponses);
        return postResponse;
    }
}
