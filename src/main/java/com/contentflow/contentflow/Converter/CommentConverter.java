package com.contentflow.contentflow.Converter;


import com.contentflow.contentflow.dto.request.CommentRequest;
import com.contentflow.contentflow.dto.response.CommentResponse;
import com.contentflow.contentflow.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentConverter {

    public static Comment commentDtoToCommentEntity(CommentRequest commentRequest){
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        return comment;
    }

    public static CommentResponse commentEntityToCommentResponse(Comment comment){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        return commentResponse;
    }
}
